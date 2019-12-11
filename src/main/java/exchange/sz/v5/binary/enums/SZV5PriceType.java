package exchange.sz.v5.binary.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author xuejian.sun
 * @date 2019/12/10 10:37
 */
public enum SZV5PriceType {
    /**
     * 未知
     */
    UNKNOWN(""),

    /**
     * 买入
     */
    BUY("0"),
    /**
     * 卖出
     */
    SELL("1"),
    /**
     * 最新价
     */
    LAST("2"),
    /**
     * 开盘价
     */
    OPEN("4"),
    /**
     * 最高价
     */
    HIGH("7"),
    /**
     * 最低价
     */
    LOW("8"),
    /**
     * 升跌1
     */
    UD1("x1"),
    /**
     * 升跌2
     */
    UD2("x2"),
    /**
     * 买入汇总
     */
    SUMMARY_BUY("x3"),
    /**
     * 卖出汇总
     */
    SUMMARY_SELL("x4"),
    /**
     * 股票市盈率1
     */
    STOCK_PE1("x5"),
    /**
     * 股票市盈率2
     */
    STOCK_PE2("x6"),
    /**
     * 基金 T-1 日净值
     */
    FUND_DNW("x7"),
    /**
     * 基金实时参考净值（包括 ETF
     * 的 IOPV）
     */
    FUND_RT_RNW("x8"),
    /**
     * 权证溢价率
     */
    WPR("x9"),
    /**
     * 涨停价
     */
    HIGH_LIMIT("xe"),
    /**
     * 跌停价
     */
    LOW_LIMIT("xf"),
    /**
     * 合约持仓量
     */
    CONTRACT_POSITION("xg")
    ;

    @Getter
    private String priceType;

    SZV5PriceType(String priceType) {
        this.priceType = priceType;
    }

    private static Map<String, SZV5PriceType> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SZV5PriceType.values()).forEach(priceType -> mapping.put(priceType.getPriceType(), priceType));
    }

    public static Optional<SZV5PriceType> lookup(String priceType) {
        return Optional.ofNullable(mapping.get(priceType));
    }
}
