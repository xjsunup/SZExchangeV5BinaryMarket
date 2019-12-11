package exchange.sz.v5.binary.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author xuejian.sun
 * @date 2019/12/11 13:16
 */
public enum SZExchangeStatus{
    /**
     * 未知
     */
    UNKNOWN("unknown"),
    /**
     * 启动（开市前）
     */
    START("S"),
    /**
     * 开盘集合竞价
     */
    OPEN_TRANSACTION("O"),
    /**
     * 连续竞价
     */
    TRANSACTION("T"),
    /**
     * 休市
     */
    BREAK("B"),
    /**
     * 收盘集合竞价
     */
    CLOSE_TRANSACTION("C"),
    /**
     * 已闭市
     */
    CLOSED("E"),
    /**
     * 临时停牌
     */
    HALT("H"),
    /**
     * 盘后交易
     */
    AFTER_CLOSE_TRANSACTION("A"),
    /**
     * 波动性中断
     */
    VOLA("V");

    @Getter
    private String status;

    SZExchangeStatus(String status) {
        this.status = status;
    }

    private static Map<String, SZExchangeStatus> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SZExchangeStatus.values()).forEach(exchangeStatus -> mapping.put(exchangeStatus.getStatus(), exchangeStatus));
    }

    public static Optional<SZExchangeStatus> lookup(String status) {
        return Optional.ofNullable(mapping.get(status));
    }
}
