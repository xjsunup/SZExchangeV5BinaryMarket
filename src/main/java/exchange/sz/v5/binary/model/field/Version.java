package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.StringField;
import lombok.NoArgsConstructor;

/**
 * 深圳Binary版本号
 *
 * @author xuejian.sun
 * @date 2019/12/4 17:35
 */
@NoArgsConstructor
public class Version extends StringField {

    public static int FIELD_LENGTH = 32;

    public Version(String version) {
        super(version, FIELD_LENGTH);
    }
}
