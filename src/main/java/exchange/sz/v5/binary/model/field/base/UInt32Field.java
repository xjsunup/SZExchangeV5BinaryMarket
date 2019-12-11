package exchange.sz.v5.binary.model.field.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.nio.ByteBuffer;

/**
 * @author xuejian.sun
 * @date 2019/12/9 15:38
 */
@ToString
@NoArgsConstructor
public class UInt32Field implements Field {

    @Getter
    protected long value;

    public UInt32Field(long value) {
        if(value == Long.MAX_VALUE){
            value = 0;
        }
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt((int) value);
        return buffer.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        this.value = byteBuf.readUnsignedInt();
    }
}
