package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.UInt16Field;
import lombok.NoArgsConstructor;

/**
 * @author xuejian.sun
 * @date 2019/12/9 15:49
 */
@NoArgsConstructor
public class MDPriceLevel extends UInt16Field {
    public MDPriceLevel(int value) {
        super(value);
    }
}
