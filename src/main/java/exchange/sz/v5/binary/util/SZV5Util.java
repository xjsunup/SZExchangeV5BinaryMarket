package exchange.sz.v5.binary.util;

import exchange.sz.v5.binary.enums.SZV5MsgType;
import exchange.sz.v5.binary.model.message.*;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author xuejian.sun
 * @date 2019/12/9 11:00
 */
public final class SZV5Util {

    private SZV5Util(){}

    private static Map<SZV5MsgType,Class<? extends Message>> pakMap;

    static {
        pakMap = new HashMap<>(16);
        pakMap.put(SZV5MsgType.HEARTBEAT, SZV5Heartbeat.class);
        pakMap.put(SZV5MsgType.LEVEL1, SZV5Level1MarketSnapshot.class);
        pakMap.put(SZV5MsgType.CHANNEL_HEARTBEAT, SZV5MarketChannelHeartbeat.class);
        pakMap.put(SZV5MsgType.LOGON, SZV5Logon.class);
        pakMap.put(SZV5MsgType.LOGOUT, SZV5Logout.class);
    }

    public static Optional<Class<? extends Message>> getMessage(SZV5MsgType msgTypeEnum){
        if(msgTypeEnum == null){
            return Optional.empty();
        }
        return Optional.ofNullable(pakMap.get(msgTypeEnum));
    }

    public static String rightPad(String value, int size, char padChar){
        StringBuilder builder = new StringBuilder(value);
        for(int i = 0; i < size - value.length(); i++) {
            builder.append(padChar);
        }
        return builder.toString();
    }

    public static String getRemoteAddress(Channel channel){
        InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress();
        int port = socketAddress.getPort();
        String hostName = socketAddress.getHostName();
        return hostName + ":" + port;
    }
}
