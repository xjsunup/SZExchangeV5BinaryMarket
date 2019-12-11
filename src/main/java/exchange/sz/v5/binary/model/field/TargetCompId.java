package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.StringField;
import lombok.NoArgsConstructor;

/**
 * 对方编号
 *
 * @author xuejian.sun
 * @date 2019/12/4 16:59
 */
@NoArgsConstructor
public class TargetCompId extends StringField {

    public static int FIELD_LENGTH = 20;

    public TargetCompId(String targetCompId) {
        super(targetCompId,FIELD_LENGTH);
    }
}
