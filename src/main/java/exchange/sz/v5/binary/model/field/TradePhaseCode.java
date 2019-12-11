package exchange.sz.v5.binary.model.field;

import exchange.sz.v5.binary.enums.SZExchangeStatus;
import exchange.sz.v5.binary.enums.SZV5SecurityStatus;
import exchange.sz.v5.binary.model.field.base.StringField;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 深圳Binary版本号
 *
 * @author xuejian.sun
 * @date 2019/12/4 17:35
 */
@NoArgsConstructor
public class TradePhaseCode extends StringField {

    public static int FIELD_LENGTH = 8;

    @Getter
    private SZV5SecurityStatus securityStatus;

    @Getter
    private SZExchangeStatus exchangeStatus;

    public TradePhaseCode(String code) {
        super(code, FIELD_LENGTH);
        this.exchangeStatus = SZExchangeStatus.lookup(value.substring(0, 1)).orElse(SZExchangeStatus.UNKNOWN);
        this.securityStatus = SZV5SecurityStatus.lookup(value.substring(1));
    }

    public TradePhaseCode(SZV5SecurityStatus securityStatus, SZExchangeStatus exchangeStatus) {
        super(exchangeStatus.getStatus() + securityStatus.getStatusCode(), FIELD_LENGTH);
        this.securityStatus = securityStatus;
        this.exchangeStatus = exchangeStatus;
    }
}
