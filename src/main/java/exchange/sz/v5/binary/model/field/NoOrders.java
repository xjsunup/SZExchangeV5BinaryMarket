package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.UInt32Field;
import lombok.NoArgsConstructor;

/**
 * @author xuejian.sun
 * @date 2019/12/9 15:50
 */
@NoArgsConstructor
public class NoOrders extends UInt32Field {
    public NoOrders(long noOrders) {
        super(noOrders);
    }
}
