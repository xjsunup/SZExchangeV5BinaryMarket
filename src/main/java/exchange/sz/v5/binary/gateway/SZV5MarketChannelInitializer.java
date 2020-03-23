package exchange.sz.v5.binary.gateway;

import exchange.sz.v5.binary.config.SZExchangeConfigure;
import exchange.sz.v5.binary.gateway.pipline.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author xuejian.sun
 * @date 2019/12/9 9:53
 */
public class SZV5MarketChannelInitializer extends ChannelInitializer {

    private SZV5MarketHandler marketHandler;

    private SZExchangeConfigure configure;

    public SZV5MarketChannelInitializer(SZV5MarketHandler marketHandler, SZExchangeConfigure configure) {
        this.marketHandler = marketHandler;
        this.configure = configure;
    }

    @Override
    protected void initChannel(Channel channel) {
        channel.pipeline()
                .addLast(new SZV5ReadIdleStateHandler(configure.getHeartbeatInterval(), 0, 0))
                .addLast(new SZV5HeartbeatLostHandler(configure.getHeartbeatInterval() * 2, 0, 0))
                .addLast(new SZV5MarketProtocolDecoder())
                .addLast(new SZV5MarketPakDecoder())
                .addLast(marketHandler)
                .addLast(new SZV5MarketPakEncoder());
    }
}
