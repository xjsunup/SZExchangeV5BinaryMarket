package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.enums.SZV5PriceType;
import exchange.sz.v5.binary.model.field.base.StringField;
import lombok.Getter;

/**
 * @author xuejian.sun
 * @date 2019/12/10 10:31
 */
public class MDEntryType extends StringField {

    public static int FIELD_LENGTH = 2;

    @Getter
    private SZV5PriceType priceTypeEnum;

    public MDEntryType(SZV5PriceType priceTypeEnum) {
        super(priceTypeEnum.getPriceType(), FIELD_LENGTH);
        this.priceTypeEnum = priceTypeEnum;
    }

    public MDEntryType(String priceType) {
        super(priceType, FIELD_LENGTH);
        this.priceTypeEnum = SZV5PriceType.lookup(super.value).orElse(SZV5PriceType.UNKNOWN);
    }
}
