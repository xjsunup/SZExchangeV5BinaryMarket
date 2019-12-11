package exchange.sz.v5.binary.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author xuejian.sun
 * @date 2019/12/6 16:11
 */
public enum SZV5MsgType {
    /**
     * 集中竞价交易行情快照
     */
    LEVEL1(300111),
    /**
     * 行情 频道心跳
     */
    CHANNEL_HEARTBEAT(390095),
    /**
     * 登陆
     */
    LOGON(1),

    /**
     * 登出
     */
    LOGOUT(2),

    /**
     * 连接心跳
     */
    HEARTBEAT(3);

    @Getter
    private int code;

    SZV5MsgType(int code) {
        this.code = code;
    }

    private static Map<Integer, SZV5MsgType> mapping;

    static {
        mapping = new HashMap<>(16);
        Arrays.stream(SZV5MsgType.values()).forEach(msgType -> mapping.put(msgType.getCode(), msgType));
    }

    public static Optional<SZV5MsgType> lookup(int msgType) {
        return Optional.ofNullable(mapping.get(msgType));
    }
}
