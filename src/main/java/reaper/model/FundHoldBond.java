package reaper.model;

import javax.persistence.*;

/**
 * 债券持仓数据
 */
@Entity
@Table(name = "fund_hold_bond")
public class FundHoldBond {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String fundCode;

    private Integer year;

    private Integer season;

    @Column(length = 7)
    private String bondCode;

    @Column(length = 20)
    private String bondName;

    private Double proportion;

    public FundHoldBond() {
    }

    public FundHoldBond(String fundCode, Integer year, Integer season, String bondCode, String bondName, Double proportion) {
        this.fundCode = fundCode;
        this.year = year;
        this.season = season;
        this.bondCode = bondCode;
        this.bondName = bondName;
        this.proportion = proportion;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
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

    public String getBondCode() {
        return bondCode;
    }

    public void setBondCode(String bondCode) {
        this.bondCode = bondCode;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    @Override
    public String toString() {
        return "FundHoldBond{" +
                "fundCode='" + fundCode + '\'' +
                ", year=" + year +
                ", season=" + season +
                ", bondCode='" + bondCode + '\'' +
                ", bondName='" + bondName + '\'' +
                ", proportion=" + proportion +
                '}';
    }
}
