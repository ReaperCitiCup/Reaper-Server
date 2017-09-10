package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "fund_attribution")
public class FundAttribution {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
//    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "fund",referencedColumnName = "code",unique = true)
    private String code;

    /**
     * 在险价值
     */
    private Double valueAtRisk;

    /**
     * 收益率序列的下行标准差
     */
    private Double standardDeviation;

    /**
     * 夏普比
     */
    private Double sharpeRiato;

    /**
     * beta
     */
    private Double beta;

    /**
     * 最近一天的alpha
     */
    private Double latestAlpha;

    /**
     * 特雷诺指数
     */
    private Double treynorRatio;

    /**
     * 择股能力
     */
    private Double stockSelectAbility;

    /**
     * 择时能力
     */
    private Double timeSelectAbility;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getValueAtRisk() {
        return valueAtRisk;
    }

    public void setValueAtRisk(Double valueAtRisk) {
        this.valueAtRisk = valueAtRisk;
    }

    public Double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Double getSharpeRiato() {
        return sharpeRiato;
    }

    public void setSharpeRiato(Double sharpeRiato) {
        this.sharpeRiato = sharpeRiato;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double getLatestAlpha() {
        return latestAlpha;
    }

    public void setLatestAlpha(Double latestAlpha) {
        this.latestAlpha = latestAlpha;
    }

    public Double getTreynorRatio() {
        return treynorRatio;
    }

    public void setTreynorRatio(Double treynorRatio) {
        this.treynorRatio = treynorRatio;
    }

    public Double getStockSelectAbility() {
        return stockSelectAbility;
    }

    public void setStockSelectAbility(Double stockSelectAbility) {
        this.stockSelectAbility = stockSelectAbility;
    }

    public Double getTimeSelectAbility() {
        return timeSelectAbility;
    }

    public void setTimeSelectAbility(Double timeSelectAbility) {
        this.timeSelectAbility = timeSelectAbility;
    }

    @Override
    public String toString() {
        return "FundAttribution{" +
                "code='" + code + '\'' +
                ", valueAtRisk=" + valueAtRisk +
                ", standardDeviation=" + standardDeviation +
                ", sharpeRiato=" + sharpeRiato +
                ", beta=" + beta +
                ", latestAlpha=" + latestAlpha +
                ", treynorRatio=" + treynorRatio +
                ", stockSelectAbility=" + stockSelectAbility +
                ", timeSelectAbility=" + timeSelectAbility +
                '}';
    }
}
