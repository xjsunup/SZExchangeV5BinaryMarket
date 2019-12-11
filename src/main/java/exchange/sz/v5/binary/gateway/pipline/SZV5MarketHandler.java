package exchange.sz.v5.binary.gateway.pipline;

import exchange.sz.v5.binary.gateway.SZV5MessageFacade;
import exchange.sz.v5.binary.model.message.Message;
import exchange.sz.v5.binary.util.SZV5Util;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuejian.sun
 * @date 2019/12/9 13:25
 */
@Slf4j
@ChannelHandler.Sharable
public class SZV5MarketHandler extends SimpleChannelInboundHandler<Message> {

    private SZV5MessageFacade facade;

    public SZV5MarketHandler(SZV5MessageFacade facade) {
        this.facade = facade;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Message message) {
        facade.onMessage(message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        facade.disconnected(String.format("server[%s] exit", SZV5Util.getRemoteAddress(ctx.channel())));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        facade.connected(String.format("connect to %s is success", SZV5Util.getRemoteAddress(ctx.channel())));
    }
}
