package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.enums.SZV5MsgType;
import lombok.Getter;

import java.nio.ByteBuffer;

/**
 * @author xuejian.sun
 * @date 2019/12/6 14:58
 */
public class SZV5MsgPak {
    @Getter
    private MsgType msgType;

    @Getter
    private byte[] msgPak;

    @Getter
    private Tail checksum;

    public SZV5MsgPak(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        int msgTypeCode = buffer.getInt();
        SZV5MsgType msgTypeEnum = SZV5MsgType.lookup(msgTypeCode)
                .orElseThrow(() -> new IllegalArgumentException("unknown msg type -> " + msgTypeCode));
        this.msgType = new MsgType(msgTypeEnum);
        int bodyLength = buffer.getInt();
        byte[] body = new byte[bodyLength];
        buffer.get(body);
        this.checksum = new Tail(buffer.getInt());
        this.msgPak = data;
    }


    /**
     * 检查checksum值
     *
     * @return bool
     */
    public boolean validateChecksum() {
        if(checksum == null || msgPak.length == 0) {
            return false;
        }
        Tail checksum = Tail.generateChecksum(msgPak);
        return checksum.getValue() == this.checksum.getValue();
    }
}
