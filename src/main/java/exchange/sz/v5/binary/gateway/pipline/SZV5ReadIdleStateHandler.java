package exchange.sz.v5.binary.gateway.pipline;

import exchange.sz.v5.binary.model.message.SZV5Heartbeat;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuejian.sun
 * @date 2019/12/11 15:48
 */
@Slf4j
public class SZV5ReadIdleStateHandler extends IdleStateHandler {

    private SZV5Heartbeat heartbeat = new SZV5Heartbeat();

    public SZV5ReadIdleStateHandler(int readerIdleTimeSeconds, int writerIdleTimeSeconds, int allIdleTimeSeconds) {
        super(readerIdleTimeSeconds, writerIdleTimeSeconds, allIdleTimeSeconds);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        ctx.channel().writeAndFlush(heartbeat);
        log.info("send heartbeat ... ");
        try {
            super.channelIdle(ctx, evt);
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
