package reaper.model;



import javax.persistence.*;
import java.util.Date;

/**
 * Created by max on 2017/9/11.
 */

@Entity
@Table(name = "basic_stock_index")
public class BasicStockIndex {


    @Id
    @GeneratedValue
    private Integer id;

    private Date date;//日期
    @Column(length = 8)
    private String stockId;//股票代码
    @Column(length = 64)
    private String stockName;//名称

    private Double closePrice;//收盘价

    public BasicStockIndex(String stockID, String stockName) {
        this.stockId=stockID;
        this.stockName=stockName;

    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStockId() {
        return stockId;
    }



    public Double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getBeforeClosePrice() {
        return beforeClosePrice;
    }

    public void setBeforeClosePrice(Double beforeClosePrice) {
        this.beforeClosePrice = beforeClosePrice;
    }

    public Double getFluctuation() {
        return fluctuation;
    }

    public void setFluctuation(Double fluctuation) {
        this.fluctuation = fluctuation;
    }

    public Double getPriceFluctuation() {
        return priceFluctuation;
    }

    public void setPriceFluctuation(Double priceFluctuation) {
        this.priceFluctuation = priceFluctuation;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    private Double highPrice;//最高价

    private Double lowPrice;//最低价

    private Double openPrice;//开盘价

    private Double beforeClosePrice;//前收盘

    private Double fluctuation;//涨跌额

    private Double priceFluctuation;//涨跌幅

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Double volume;//成交量

    private Double transactionAmount;//成交金额
    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }


}
