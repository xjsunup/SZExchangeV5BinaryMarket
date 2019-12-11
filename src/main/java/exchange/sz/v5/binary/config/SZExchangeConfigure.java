package exchange.sz.v5.binary.config;

import lombok.Data;

/**
 * @author xuejian.sun
 * @date 2019/12/9 10:00
 */
@Data
public class SZExchangeConfigure {
    /**
     * ip
     */
    private String serverHost;
    /**
     * 端口
     */
    private int serverPort;
    /**
     * 密码
     */
    private String password;
    /**
     * 心跳间隔
     */
    private int heartbeatInterval;
    /**
     * 目标编号
     */
    private String targetCompId;
    /**
     * 发送方编号
     */
    private String senderCompId;
    /**
     * 版本信息
     */
    private String version;
    /**
     * 重连次数
     */
    private int reconnect;
    /**
     * 重连间隔
     */
    private int reconnectInterval;
}
