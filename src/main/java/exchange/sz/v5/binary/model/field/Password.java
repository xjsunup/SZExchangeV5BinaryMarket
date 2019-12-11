package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.StringField;
import lombok.NoArgsConstructor;

/**
 * @author xuejian.sun
 * @date 2019/12/4 17:30
 */
@NoArgsConstructor
public class Password extends StringField {

    public static int FIELD_LENGTH = 16;

    public Password(String password) {
        super(password, FIELD_LENGTH);
    }
}
