package exchange.sz.v5.binary.gateway;

import exchange.sz.v5.binary.model.message.*;

/**
 * @author xuejian.sun
 * @date 2019/12/5 15:22
 */
public interface SZV5Gateway {
    /**
     * 频道心跳
     *
     * @param channelHeartbeat 频道心跳
     */
    void onChannelHeartbeat(SZV5MarketChannelHeartbeat channelHeartbeat);

    /**
     * 心跳消息
     *
     * @param heartbeat 心跳消息
     */
    void onHeartbeat(SZV5Heartbeat heartbeat);

    /**
     * 已连接
     *
     * @param message 连接消息
     */
    void connected(String message);

    /**
     * 连接断开
     *
     * @param message msg
     */
    void disconnected(String message);

    /**
     * 登陆成功消息
     *
     * @param logon msg
     */
    void onLogon(SZV5Logon logon);

    /**
     * 登出成功消息
     *
     * @param logout msg
     */
    void onLogout(SZV5Logout logout);

    /**
     * level 行情推送
     *
     * @param level1MarketSnapshot level1 msg;
     */
    void onLevel1Market(SZV5Level1MarketSnapshot level1MarketSnapshot);
}
