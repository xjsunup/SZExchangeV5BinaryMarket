package exchange.sz.v5.binary.model.message;

import exchange.sz.v5.binary.enums.SZV5PriceType;
import exchange.sz.v5.binary.model.field.MDEntryType;
import exchange.sz.v5.binary.model.field.MDPriceLevel;
import exchange.sz.v5.binary.model.field.NoMDEntries;
import exchange.sz.v5.binary.model.field.NoOrders;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/** 300111 集中竞价交易行情
 * @author xuejian.sun
 * @date 2019/12/10 10:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SZV5Level1MarketSnapshot extends SZV5MarketSnapshot {

    private List<Double> offerPrices = new LinkedList<>();

    private List<Double> bidPrices = new LinkedList<>();

    private List<Long> offerVolumes = new LinkedList<>();

    private List<Long> bidVolumes = new LinkedList<>();
    /**
     * 最新价
     */
    private double lastPx;
    /**
     * 开盘价
     */
    private double openPx;
    /**
     * 最高价
     */
    private double highPx;
    /**
     * 最低价
     */
    private double lowPx;
    /**
     * 升跌1
     */
    private double udPx1;
    /**
     * 升跌2
     */
    private double udPx2;
    /**
     * 买入汇总
     */
    private double summaryBuyPx;
    /**
     * 卖出汇总
     */
    private double summarySellPx;
    /**
     * 股票市盈率1
     */
    private double stockPERatio1;

    /**
     * 股票市盈率2
     */
    private double stockPERatio2;
    /**
     * 基金 T-1 日净值
     */
    private double fundNetWorth;
    /**
     * 基金实时参考净值
     */
    private double fundRtReferenceNetWorth;
    /**
     * 权证溢价率
     */
    private double shareWarrantPremiumRate;
    /**
     * 涨停价
     */
    private double highLimitPx;
    /**
     * 跌停价
     */
    private double lowLimitPx;
    /**
     * 合约持仓量
     */
    private double contractPosition;

    private long numberOfOrders;
    /**
     * 委托数量
     */
    private List<Long> orderQty;

    public void addBidPx(double px) {
        bidPrices.add(px);
    }

    public void addBidPx(double px, int level) {
        bidPrices.add(level, px);
    }

    public void addBidVolume(long volume) {
        bidVolumes.add(volume);
    }

    public void addBidVolume(long volume, int level) {
        bidVolumes.add(level, volume);
    }

    public void addOfferPx(double px) {
        offerPrices.add(px);
    }

    public void addOfferPx(double px, int level) {
        offerPrices.add(level, px);
    }

    public void addOfferVolume(long volume) {
        offerVolumes.add(volume);
    }

    public void addOfferVolume(long volume, int level) {
        offerVolumes.add(level, volume);
    }

    @Override
    public long getTotalVolumeTrade() {
        return super.getTotalVolumeTrade() / 100;
    }

    @Override
    public long getTotalValueTrade() {
        return super.getTotalValueTrade() / 10000;
    }

    @Override
    protected void read(ByteBuf body) {
        super.read(body);
        //重复组, 代表有多少个档位
        NoMDEntries noMDEntries = new NoMDEntries(body.readUnsignedInt());
        for(int i = 0; i < noMDEntries.getValue(); i++) {
            MDEntryType mdEntryType = new MDEntryType(
                    body.readCharSequence(MDEntryType.FIELD_LENGTH, StandardCharsets.UTF_8).toString());
            SZV5PriceType priceTypeEnum = mdEntryType.getPriceTypeEnum();
            long mdEntryPx = body.readLong();
            long mdEntrySize = body.readLong();
            MDPriceLevel mdPriceLevel = new MDPriceLevel(body.readUnsignedShort());
            setPriceDetail(priceTypeEnum, mdPriceLevel.getValue() -1, mdEntrySize, mdEntryPx);
            this.numberOfOrders = body.readLong();
            NoOrders noOrders = new NoOrders(body.readUnsignedInt());
            if(noOrders.getValue() != 0) {
                this.orderQty = new LinkedList<>();
            }
            for(int j = 0; j < noOrders.getValue(); j++) {
                orderQty.add(body.readLong() / 100);
            }
        }
    }

    private void setPriceDetail(SZV5PriceType priceType, int level, long volume, long price) {
        if(priceType == null) {
            return;
        }
        double px = price / 1000000.0;
        switch(priceType) {
            case BUY:
                addBidPx(px, level);
                addBidVolume(volume / 100, level);
                break;
            case SELL:
                addOfferPx(px, level);
                addOfferVolume(volume / 100, level);
                break;
            case LAST:
                this.lastPx = px;
                break;
            case OPEN:
                this.openPx = px;
                break;
            case HIGH:
                this.highPx = px;
                break;
            case LOW:
                this.lowPx = px;
                break;
            case HIGH_LIMIT:
                this.highLimitPx = px;
                break;
            case LOW_LIMIT:
                this.lowLimitPx = px;
                break;
            case UD1:
                this.udPx1 = px;
                break;
            case UD2:
                this.udPx2 = px;
                break;
            case SUMMARY_BUY:
                this.summaryBuyPx = px;
                break;
            case SUMMARY_SELL:
                this.summarySellPx = px;
                break;
            case STOCK_PE1:
                this.stockPERatio1 = px;
                break;
            case STOCK_PE2:
                this.stockPERatio2 = px;
                break;
            case FUND_DNW:
                this.fundNetWorth = px;
                break;
            case FUND_RT_RNW:
                this.fundRtReferenceNetWorth = px;
                break;
            case WPR:
                this.shareWarrantPremiumRate = px;
                break;
            case CONTRACT_POSITION:
                this.contractPosition = px;
                break;
            default:
                break;
        }
    }
}
