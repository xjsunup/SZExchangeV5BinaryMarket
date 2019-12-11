package exchange.sz.v5.binary.model.field.base;

import exchange.sz.v5.binary.util.BinaryUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.nio.ByteBuffer;

/**
 * @author xuejian.sun
 * @date 2019/12/5 13:29
 */
@ToString
@NoArgsConstructor
public class IntField implements Field<Integer> {

    @Getter
    protected int value;

    public IntField(int value) {
        if(value == Integer.MAX_VALUE){
            value = 0;
        }
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(value);
        return buffer.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        this.value = BinaryUtil.bytesToInt(bytes);
    }
}
