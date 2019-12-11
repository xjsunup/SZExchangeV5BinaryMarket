package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.StringField;

/**
 * @author xuejian.sun
 * @date 2019/12/9 15:02
 */
public class SecurityIdSource extends StringField {

    public static int FIELD_LENGTH = 4;

    public SecurityIdSource(String streamId) {
        super(streamId, 4);
    }
}
