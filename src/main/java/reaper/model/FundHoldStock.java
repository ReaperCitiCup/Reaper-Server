package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "fund_hold_stock")
public class FundHoldStock {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String fundCode;

    private Integer year;

    private Integer season;

    @Column(length = 7)
    private String stockCode;

    private Double proportion;

    public FundHoldStock() {
    }

    public FundHoldStock(String fundCode, Integer year, Integer season, String stockCode, Double proportion) {
        this.fundCode = fundCode;
        this.year = year;
        this.season = season;
        this.stockCode = stockCode;
        this.proportion = proportion;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Override
    public String toString() {
        return "FundHoldStock{" +
                "fundCode='" + fundCode + '\'' +
                ", year=" + year +
                ", season=" + season +
                ", stockCode='" + stockCode + '\'' +
                ", proportion=" + proportion +
                '}';
    }
}
