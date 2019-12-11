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
public class UInt16Field implements Field {

    @Getter
    protected int value;

    public UInt16Field(int value) {
        if(value == Integer.MAX_VALUE){
            value = 0;
        }
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort((short) value);
        return buffer.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        this.value = byteBuf.readUnsignedShort();
    }
}
