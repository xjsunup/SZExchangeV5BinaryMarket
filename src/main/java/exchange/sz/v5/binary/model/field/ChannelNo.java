package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.UInt16Field;
import lombok.NoArgsConstructor;

/**
 * @author xuejian.sun
 * @date 2019/12/9 15:55
 */
@NoArgsConstructor
public class ChannelNo extends UInt16Field {

    public ChannelNo(int value) {
        super(value);
    }
}
