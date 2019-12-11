package exchange.sz.v5.binary.gateway.pipline;

import exchange.sz.v5.binary.enums.SZV5MsgType;
import exchange.sz.v5.binary.gateway.codec.SZV5MessageSerializer;
import exchange.sz.v5.binary.model.field.Tail;
import exchange.sz.v5.binary.model.message.Message;
import exchange.sz.v5.binary.util.SZV5Util;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author xuejian.sun
 * @date 2019/12/9 10:28
 */
public class SZV5MarketPakDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) {
        int msgType = byteBuf.getInt(0);
        int bodyLength = byteBuf.getInt(4);
        long checksum = byteBuf.getUnsignedInt(8 + bodyLength);
        byte[] data = new byte[8 + bodyLength + 4];
        byteBuf.readBytes(data);
        //检查这条数据的checksum算法是否与我本地算法一致.
        if(checksum != Tail.generateChecksum(data).getValue()) {
            return;
        }
        SZV5MessageSerializer serializer = new SZV5MessageSerializer();
        SZV5MsgType.lookup(msgType)
                .flatMap(SZV5Util::getMessage)
                .ifPresent(msgClass -> {
                    Message message = serializer.deserialize(msgClass, data);
                    out.add(message);
                });
    }
}
