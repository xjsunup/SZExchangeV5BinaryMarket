package exchange.sz.v5.binary.gateway.pipline;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/** 解决拆包粘包问题
 *
 * @author xuejian.sun
 * @date 2019/12/9 10:11
 */
@Slf4j
public class SZV5MarketProtocolDecoder extends LengthFieldBasedFrameDecoder {

    public SZV5MarketProtocolDecoder() {
        super(Integer.MAX_VALUE, 4, 4,4,0);
    }
}
