package exchange.sz.v5.binary.model.message;

import exchange.sz.v5.binary.enums.SZV5MsgType;
import exchange.sz.v5.binary.model.field.BodyLength;
import exchange.sz.v5.binary.model.field.MsgType;
import exchange.sz.v5.binary.model.field.Tail;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * @author xuejian.sun
 * @date 2019/12/4 16:50
 */
@Slf4j
@Data
public abstract class Message {

    protected MsgType msgType;

    private BodyLength bodyLength;

    private Tail checksum;

    public Message() {
    }

    Message(MsgType msgType) {
        this.msgType = msgType;
    }

    /**
     * 将所有字段转为byte
     *
     * @return byte array
     */
    protected abstract byte[] toBytes();

    /**
     * 从字节流中恢复出对象
     *
     * @param body data array
     */
    protected abstract void read(ByteBuf body);

    /**
     * 从字节数组中恢复当前对象
     *
     * @param data 数据
     */
    public void resumeMessageFrom(byte[] data) {
        try {
            ByteBuf buffer = Unpooled.wrappedBuffer(data);
            int msgTypeCode = buffer.readInt();
            SZV5MsgType msgTypeEnum = SZV5MsgType.lookup(msgTypeCode)
                    .orElseThrow(() -> new IllegalArgumentException("unknown msg type: " + msgTypeCode));
            this.msgType = new MsgType(msgTypeEnum);
            this.bodyLength = new BodyLength(buffer.readInt());
            byte[] body = new byte[bodyLength.getValue()];
            buffer.readBytes(body);
            read(Unpooled.wrappedBuffer(body));
            this.checksum = new Tail(buffer.readInt());
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 将该消息转换成SZV5协议.
     *
     * @return byte array
     */
    public byte[] toSZV5BinaryMessage() {
        byte[] body = toBytes();
        ByteBuffer buffer = ByteBuffer.allocate(8 + body.length + 4);
        buffer.putInt(msgType.getValue());
        buffer.putInt(body.length);
        buffer.put(body);
        byte[] msgCompletePak = buffer.array();
        this.checksum = Tail.generateChecksum(msgCompletePak);
        byte[] checksumByte = checksum.toBytes();
        System.arraycopy(checksumByte, 0, msgCompletePak, msgCompletePak.length - 4, 4);
        return msgCompletePak;
    }

    public Tail getCheckSum() {
        if(checksum == null) {
            byte[] body = toBytes();
            ByteBuffer buffer = ByteBuffer.allocate(4 + body.length + 4);
            buffer.putInt(msgType.getValue());
            buffer.put(body);
            byte[] hearBodyArray = buffer.array();
            this.checksum = Tail.generateChecksum(hearBodyArray);
        }
        return checksum;
    }

    public BodyLength getBodyLength() {
        if(bodyLength == null) {
            return new BodyLength(toBytes().length);
        }
        return bodyLength;
    }

    public MsgType getMsgType() {
        return msgType;
    }
}
