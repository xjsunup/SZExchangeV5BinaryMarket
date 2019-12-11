package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.StringField;

/**
 * @author xuejian.sun
 * @date 2019/12/10 9:50
 */
public class SecurityId extends StringField {

    public static int FIELD_LENGTH = 8;

    public SecurityId(String securityId) {
        super(securityId, FIELD_LENGTH);
    }
}
