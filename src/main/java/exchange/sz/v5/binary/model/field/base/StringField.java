package exchange.sz.v5.binary.model.field.base;

import exchange.sz.v5.binary.util.SZV5Util;
import lombok.Getter;
import lombok.ToString;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author xuejian.sun
 * @date 2019/12/4 17:26
 */
public class StringField implements Field {

    private int byteSize;

    @Getter
    protected String value;

    public StringField(String value, int byteSize) {
        this.value = value.trim();
        this.byteSize = byteSize;
    }

    public StringField() {
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer allocate = ByteBuffer.allocate(byteSize);
        allocate.put(SZV5Util.rightPad(value, byteSize, ' ').getBytes(StandardCharsets.UTF_8));
        return allocate.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        value = new String(bytes, StandardCharsets.UTF_8).trim();
    }

    @Override
    public String toString() {
        return value;
    }
}
