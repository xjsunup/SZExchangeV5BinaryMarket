package exchange.sz.v5.binary.gateway;

import exchange.sz.v5.binary.config.SZExchangeConfigure;
import exchange.sz.v5.binary.gateway.pipline.SZV5MarketHandler;
import exchange.sz.v5.binary.model.message.Message;
import exchange.sz.v5.binary.model.message.SZV5Logon;
import exchange.sz.v5.binary.model.message.SZV5Logout;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author xuejian.sun
 * @date 2019/12/7 16:45
 */
@Slf4j
public class SZV5MarketBootstrap {

    private static volatile SZV5MarketBootstrap INSTANCE;

    private volatile boolean running;

    private Bootstrap bootstrap;

    private Channel channel;

    private SZExchangeConfigure config;

    private SZV5MessageFacade facade;

    /**
     * 本次重连的数量累计
     */
    private int reconnectCount = 1;

    public static SZV5MarketBootstrap getInstance(SZExchangeConfigure config, SZV5MessageFacade szv5MessageFacade) {
        if(INSTANCE == null) {
            synchronized (SZV5MarketBootstrap.class) {
                if(INSTANCE == null) {
                    INSTANCE = new SZV5MarketBootstrap(config, szv5MessageFacade);
                }
            }
        }
        return INSTANCE;
    }

    private SZV5MarketBootstrap(SZExchangeConfigure config, SZV5MessageFacade szv5MessageFacade) {
        this.config = config;
        this.facade = szv5MessageFacade;
    }

    public boolean connect() {
        if(!running) {
            NioEventLoopGroup workerGroup = new NioEventLoopGroup(5);
            this.bootstrap = new Bootstrap();
            this.bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new SZV5MarketChannelInitializer(new SZV5MarketHandler(facade), config));
            try {
                ChannelFuture future = bootstrap.connect(config.getServerHost(), config.getServerPort()).sync();
                if(future.isSuccess()) {
                    channel = future.channel();
                    running = true;
                    log.info("connect to server success -> {}:{}", config.getServerHost(), config.getServerPort());
                } else {
                    log.info("connect to server failure -> {}:{}", config.getServerHost(), config.getServerPort());
                    reconnect(true, true);
                }
            } catch (InterruptedException e) {
                log.error("", e);
                reconnect(true, true);
            }
        }
        return running;
    }

    /**
     * 重连
     *
     * @param exitSystem 重连到达指定次数后，依旧没有连上，是否退出系统
     * @param logError   是否打印重连过程中的错误信息
     */
    public synchronized void reconnect(boolean exitSystem, boolean logError) {
        disconnect();
        int reconnect = config.getReconnect();
        if(config.getReconnect() == 0) {
            log.info("initReconnect is 0");
            reconnect = Integer.MAX_VALUE;
        }
        while(1 <= reconnect) {
            try {
                log.info("第{}次开始重连...", reconnectCount);
                ChannelFuture sync = bootstrap.connect(config.getServerHost(), config.getServerPort()).sync();
                if(sync.isSuccess()) {
                    channel = sync.channel();
                    this.running = true;
                    this.reconnectCount = 1;
                    log.info("connect to server success -> {}:{}", config.getServerHost(), config.getServerPort());
                    return;
                }
                TimeUnit.SECONDS.sleep(config.getReconnectInterval());
            } catch (Exception e) {
                if(logError) {
                    log.error("initReconnect failure", e);
                }
                try {
                    TimeUnit.SECONDS.sleep(config.getReconnectInterval());
                } catch (InterruptedException e1) {
                    if(logError) {
                        log.error("", e1);
                    }
                }
            }
            reconnectCount++;
            reconnect--;
        }
        if(!isConnect() && exitSystem) {
            log.error("重连失败，退出系统.");
            System.exit(10);
        }
    }

    public void login() {
        SZV5Logon logon = new SZV5Logon(config.getPassword(), config.getSenderCompId()
                , config.getTargetCompId(), config.getHeartbeatInterval(), config.getVersion());
        sendMessage(logon);
        log.info("send login to server {}:{}", config.getServerHost(), config.getServerPort());
    }

    public void logout() {
        SZV5Logout logout = new SZV5Logout();
        sendMessage(logout);
        log.info("send logout to server {}:{}", config.getServerHost(), config.getServerPort());
        channel.close();
    }

    public void sendMessage(Message message) {
        if(isConnect()) {
            channel.writeAndFlush(message);
        }
    }

    /**
     * 尝试重连到连上了为止
     */
    public void keepReconnect() {
        disconnect();
        if(channel != null && channel.isActive()) {
            return;
        }
        log.info("后台持续重连中 -> {}:{}", config.getServerHost(), config.getServerPort());
        while(channel == null || !channel.isActive()) {
            reconnect(false, false);
        }
    }

    public synchronized void disconnect() {
        if(channel != null && channel.isActive()) {
            channel.close();
        }
    }

    private boolean isConnect() {
        return running && channel != null && channel.isOpen() && channel.isActive();
    }
}
