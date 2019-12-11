package exchange.sz.v5.binary.model.field;


import exchange.sz.v5.binary.model.field.base.StringField;
import lombok.NoArgsConstructor;

/**
 * 发送方编号
 *
 * @author xuejian.sun
 * @date 2019/12/4 16:59
 */
@NoArgsConstructor
public class SenderCompId extends StringField {

    public static int FIELD_LENGTH = 20;

    public SenderCompId(String senderCompId) {
        super(senderCompId, FIELD_LENGTH);
    }
}
