package exchange.sz.v5.binary.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author xuejian.sun
 * @date 2019/12/11 13:02
 */
public enum SZV5SecurityStatus {
    /**
     * 未知状态
     */
    UNKNOWN("unknown"),
    /**
     * 正常状态
     */
    NORMAL("0"),
    /**
     * 停牌一天
     */
    HALT_DAY("1");

    @Getter
    private String statusCode;

    SZV5SecurityStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public static SZV5SecurityStatus lookup(String statusCode) {
        return Arrays.stream(SZV5SecurityStatus.values())
                .filter(status -> status.getStatusCode().equalsIgnoreCase(statusCode))
                .findFirst()
                .orElse(SZV5SecurityStatus.UNKNOWN);
    }
}
