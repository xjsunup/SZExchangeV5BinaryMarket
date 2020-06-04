package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.StringField;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author xuejian.sun
 * @date 2019/12/9 16:01
 */
@NoArgsConstructor
public class MDStreamId extends StringField {

    public static int FIELD_LENGTH = 3;

    public MDStreamId(String value) {
        super(value, 3);
    }
}
