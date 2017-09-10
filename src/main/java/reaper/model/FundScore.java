package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "fund_score")
public class FundScore {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
//    @OneToOne(optional = false, cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "fund",referencedColumnName = "code",unique = true)
    private String code;

    /**
     * 年化收益率
     */
    private Integer annualProfit;

    /**
     * 波动率
     */
    private Integer residualvolatility;

    /**
     * 在险价值
     */
    private Integer valueAtRisk;

    /**
     * 收益率序列的下行标准差
     */
    private Integer standardDeviation;

    /**
     * 夏普比
     */
    private Integer sharpeRiato;

    /**
     * beta
     */
    private Integer beta;

    /**
     * 最近一天的alpha
     */
    private Integer latestAlpha;

    /**
     * 特雷诺指数
     */
    private Integer treynorRatio;

    /**
     * 择股能力
     */
    private Integer stockSelectAbility;

    /**
     * 择时能力
     */
    private Integer timeSelectAbility;

    /**
     * CPR指数
     */
    private Integer cpr;

    /**
     * 现任经理平均分
     */
    private Double managerAvg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getValueAtRisk() {
        return valueAtRisk;
    }

    public void setValueAtRisk(Integer valueAtRisk) {
        this.valueAtRisk = valueAtRisk;
    }

    public Integer getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(Integer standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public Integer getSharpeRiato() {
        return sharpeRiato;
    }

    public void setSharpeRiato(Integer sharpeRiato) {
        this.sharpeRiato = sharpeRiato;
    }

    public Integer getBeta() {
        return beta;
    }

    public void setBeta(Integer beta) {
        this.beta = beta;
    }

    public Integer getLatestAlpha() {
        return latestAlpha;
    }

    public void setLatestAlpha(Integer latestAlpha) {
        this.latestAlpha = latestAlpha;
    }

    public Integer getTreynorRatio() {
        return treynorRatio;
    }

    public void setTreynorRatio(Integer treynorRatio) {
        this.treynorRatio = treynorRatio;
    }

    public Integer getStockSelectAbility() {
        return stockSelectAbility;
    }

    public void setStockSelectAbility(Integer stockSelectAbility) {
        this.stockSelectAbility = stockSelectAbility;
    }

    public Integer getTimeSelectAbility() {
        return timeSelectAbility;
    }

    public void setTimeSelectAbility(Integer timeSelectAbility) {
        this.timeSelectAbility = timeSelectAbility;
    }

    public Integer getAnnualProfit() {
        return annualProfit;
    }

    public void setAnnualProfit(Integer annualProfit) {
        this.annualProfit = annualProfit;
    }

    public Integer getResidualvolatility() {
        return residualvolatility;
    }

    public void setResidualvolatility(Integer residualvolatility) {
        this.residualvolatility = residualvolatility;
    }

    public Integer getCpr() {
        return cpr;
    }

    public void setCpr(Integer cpr) {
        this.cpr = cpr;
    }

    public Double getManagerAvg() {
        return managerAvg;
    }

    public void setManagerAvg(Double managerAvg) {
        this.managerAvg = managerAvg;
    }

    @Override
    public String toString() {
        return "FundScore{" +
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
