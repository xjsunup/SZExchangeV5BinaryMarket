package exchange.sz.v5.binary.gateway.pipline;

import exchange.sz.v5.binary.gateway.SZV5MarketBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuejian.sun
 * @date 2019/12/11 16:17
 */
@Slf4j
public class SZV5HeartbeatLostHandler extends IdleStateHandler {

    public SZV5HeartbeatLostHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        log.info("心跳超时 ... 准备重连");
        SZV5MarketBootstrap bootstrap = SZV5MarketBootstrap.getInstance(null, null);
        bootstrap.keepReconnect();
    }
}
