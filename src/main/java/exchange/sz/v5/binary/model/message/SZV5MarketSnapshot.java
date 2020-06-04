package exchange.sz.v5.binary.model.message;

import exchange.sz.v5.binary.model.field.*;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.nio.charset.StandardCharsets;

/**
 * @author xuejian.sun
 * @date 2019/12/4 16:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SZV5MarketSnapshot extends Message{
    /**
     * 数据生成时间
     */
    protected long tradeTime;
    /**
     * 频道代码
     */
    protected ChannelNo channelNo;
    /**
     * 行情类别
     */
    protected MDStreamId mdStreamId;
    /**
     * 证券代码
     */
    protected SecurityId securityId;
    /**
     * 证券代码源
     */
    protected SecurityIdSource securityIdSource;
    /**
     * 交易状态
     */
    protected TradePhaseCode tradePhaseCode;
    /**
     * 昨收价 , 转成实际价格需要 除以 10000.
     */
    protected PrevClosePrice prevClosePx;
    /**
     * 成交笔数
     */
    protected long numberTrades;
    /**
     * 成交总量
     */
    protected long totalVolumeTrade;
    /**
     * 成交总金额
     */
    protected long totalValueTrade;

    @Override
    protected byte[] toBytes() {
        return new byte[0];
    }

    @Override
    protected void read(ByteBuf body) {
        this.tradeTime = body.readLong();
        this.channelNo = new ChannelNo(body.readUnsignedShort());
        this.mdStreamId = new MDStreamId(body.readCharSequence(MDStreamId.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.securityId = new SecurityId(body.readCharSequence(SecurityId.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.securityIdSource = new SecurityIdSource(body.readCharSequence(SecurityIdSource.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.tradePhaseCode = new TradePhaseCode(body.readCharSequence(TradePhaseCode.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
        this.prevClosePx = new PrevClosePrice(body.readLong());
        this.numberTrades = body.readLong();
        this.totalVolumeTrade = body.readLong();
        this.totalValueTrade = body.readLong();
    }
}
