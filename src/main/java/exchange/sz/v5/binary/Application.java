package exchange.sz.v5.binary;

import exchange.sz.v5.binary.config.SZExchangeConfigure;
import exchange.sz.v5.binary.gateway.SZV5MarketBootstrap;
import exchange.sz.v5.binary.gateway.SZV5MessageFacade;

import java.util.concurrent.CountDownLatch;

/**
 * @author xuejian.sun
 * @date 2019/12/4 16:11
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {

        SZExchangeConfigure configure = new SZExchangeConfigure();
        configure.setReconnect(5);
        configure.setReconnectInterval(10);
        configure.setServerHost("127.0.0.1");
        configure.setServerPort(12345);
        configure.setHeartbeatInterval(10);
        configure.setPassword("");
        configure.setSenderCompId("test");
        configure.setTargetCompId("test_Send");
        configure.setVersion("1.01");
        SZV5MarketBootstrap bootstrap = SZV5MarketBootstrap.getInstance(configure,new SZV5MessageFacade());
        Runtime.getRuntime().addShutdownHook(new Thread(bootstrap::logout));
        boolean connect = bootstrap.connect();
        if(connect){
            bootstrap.login();
        }
        new CountDownLatch(1).await();
    }
}
