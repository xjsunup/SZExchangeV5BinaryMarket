package exchange.sz.v5.binary.model.message;

import exchange.sz.v5.binary.enums.SZV5MsgType;
import exchange.sz.v5.binary.model.field.Text;
import exchange.sz.v5.binary.model.field.MsgType;
import io.netty.buffer.ByteBuf;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author xuejian.sun
 * @date 2019/12/6 14:00
 */
@Getter
public class SZV5Logout extends Message{

    private int sessionStatus;

    private Text text;

    public SZV5Logout() {
        super(new MsgType(SZV5MsgType.LOGOUT));
    }

    @Override
    protected byte[] toBytes() {
        byte[] textByte = text.toBytes();
        ByteBuffer buffer = ByteBuffer.allocate(4 + textByte.length);
        buffer.putInt(sessionStatus);
        buffer.put(textByte);
        return buffer.array();
    }

    @Override
    protected void read(ByteBuf body) {
        this.sessionStatus = body.readInt();
        this.text = new Text(body.readCharSequence(Text.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
    }

    @Override
    public String toString() {
        return "SZV5Logout{" +
                "sessionStatus=" + sessionStatus +
                ", text=" + text +
                '}';
    }
}
