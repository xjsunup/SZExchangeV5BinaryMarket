package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.StringField;

/**
 * @author xuejian.sun
 * @date 2019/12/6 14:11
 */
public class Text extends StringField {

    public static int FIELD_LENGTH = 200;

    public Text(String text) {
        super(text, FIELD_LENGTH);
    }

    public Text() {

    }
}
