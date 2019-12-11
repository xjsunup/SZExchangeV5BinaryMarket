package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.UInt32Field;
import lombok.NoArgsConstructor;

/**
 * @author xuejian.sun
 * @date 2019/12/9 15:31
 */
@NoArgsConstructor
public class NoMDEntries extends UInt32Field {

    public NoMDEntries(long value) {
        super(value);
    }
}
