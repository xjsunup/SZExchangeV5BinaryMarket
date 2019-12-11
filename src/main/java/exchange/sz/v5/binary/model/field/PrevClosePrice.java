package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.model.field.base.PriceField;

/**
 * @author xuejian.sun
 * @date 2019/12/11 12:00
 */
public class PrevClosePrice extends PriceField {

    public PrevClosePrice(long value) {
        super(value);
    }

    @Override
    public double getDoubleValue() {
        return super.getDoubleValue() / 10000.0;
    }
}
