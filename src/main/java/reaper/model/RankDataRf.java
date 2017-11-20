package reaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rank_data_rf")
public class RankDataRf {
    @Id
    @Column(length = 6)
    public String code;

    private Double beta;

    private Double btop;

    private Double earningsYield;

    private Double growth;

    private Double leverage;

    private Double liquidity;

    private Double momentum;

    private Double nlsize;

    private Double residualvolatility;

    private Double size;

    public RankDataRf() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double getBtop() {
        return btop;
    }

    public void setBtop(Double btop) {
        this.btop = btop;
    }

    public Double getEarningsYield() {
        return earningsYield;
    }

    public void setEarningsYield(Double earningsYield) {
        this.earningsYield = earningsYield;
    }

    public Double getGrowth() {
        return growth;
    }

    public void setGrowth(Double growth) {
        this.growth = growth;
    }

    public Double getLeverage() {
        return leverage;
    }

    public void setLeverage(Double leverage) {
        this.leverage = leverage;
    }

    public Double getLiquidity() {
        return liquidity;
    }

    public void setLiquidity(Double liquidity) {
        this.liquidity = liquidity;
    }

    public Double getMomentum() {
        return momentum;
    }

    public void setMomentum(Double momentum) {
        this.momentum = momentum;
    }

    public Double getNlsize() {
        return nlsize;
    }

    public void setNlsize(Double nlsize) {
        this.nlsize = nlsize;
    }

    public Double getResidualvolatility() {
        return residualvolatility;
    }

    public void setResidualvolatility(Double residualvolatility) {
        this.residualvolatility = residualvolatility;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "RankDataRf{" +
                "code='" + code + '\'' +
                ", beta=" + beta +
                ", btop=" + btop +
                ", earningsYield=" + earningsYield +
                ", growth=" + growth +
                ", leverage=" + leverage +
                ", liquidity=" + liquidity +
                ", momentum=" + momentum +
                ", nlsize=" + nlsize +
                ", residualvolatility=" + residualvolatility +
                ", size=" + size +
                '}';
    }
}
