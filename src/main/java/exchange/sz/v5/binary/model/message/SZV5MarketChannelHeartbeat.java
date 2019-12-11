package exchange.sz.v5.binary.model.message;

import exchange.sz.v5.binary.model.field.AppLastSeqNum;
import exchange.sz.v5.binary.model.field.ChannelNo;
import exchange.sz.v5.binary.model.field.EndOfChannel;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** 行情频道消息
 *
 * @author xuejian.sun
 * @date 2019/12/9 16:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SZV5MarketChannelHeartbeat extends Message{

    private ChannelNo channelNo;

    private AppLastSeqNum appLastSeqNum;

    private EndOfChannel endOfChannel;

    @Override
    protected byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void read(ByteBuf body) {
        this.channelNo = new ChannelNo(body.readUnsignedShort());
        this.appLastSeqNum = new AppLastSeqNum(body.readUnsignedShort());
        this.endOfChannel = new EndOfChannel(body.readUnsignedShort());
    }
}
