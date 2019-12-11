package exchange.sz.v5.binary.gateway.pipline;

import exchange.sz.v5.binary.model.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xuejian.sun
 * @date 2019/12/9 13:31
 */
public class SZV5MarketPakEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) {
        byte[] data = message.toSZV5BinaryMessage();
        byteBuf.writeBytes(data);
    }
}
