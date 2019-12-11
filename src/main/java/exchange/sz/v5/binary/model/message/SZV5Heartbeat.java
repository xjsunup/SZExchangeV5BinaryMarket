package exchange.sz.v5.binary.model.message;

import exchange.sz.v5.binary.enums.SZV5MsgType;
import exchange.sz.v5.binary.model.field.MsgType;
import io.netty.buffer.ByteBuf;

/** 心跳消息
 *
 * @author xuejian.sun
 * @date 2019/12/7 15:43
 */
public class SZV5Heartbeat extends Message {

    public SZV5Heartbeat() {
        super(new MsgType(SZV5MsgType.HEARTBEAT));
    }

    @Override
    protected byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void read(ByteBuf body) {

    }

    @Override
    public String toString() {
        return "SZV5Heartbeat{" +
                "msgType=" + msgType +
                '}';
    }
}
