package reaper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by max on 2017/9/10.
 */
@Entity
@Table(name = "manager_remark")


public class ManagerRemark {





    @Id
    @GeneratedValue
    private Integer id;

    private Integer managerId;//基金经理ID

    private Double averageYieldRate;//收益率均值

    private Integer yieldAbility;//收益能力

    private  Double averageFluctuationRatio;//波动率均值

    private Integer windControlAbility;//风控能力

    private Double timingCoefficient;//择时系数

    private Integer marketTimingAbility;//择时能力

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Double getAverageYieldRate() {
        return averageYieldRate;
    }

    public void setAverageYieldRate(Double averageYieldRate) {
        this.averageYieldRate = averageYieldRate;
    }

    public Integer getYieldAbility() {
        return yieldAbility;
    }

    public void setYieldAbility(Integer yieldAbility) {
        this.yieldAbility = yieldAbility;
    }

    public Double getAverageFluctuationRatio() {
        return averageFluctuationRatio;
    }

    public void setAverageFluctuationRatio(Double averageFluctuationRatio) {
        this.averageFluctuationRatio = averageFluctuationRatio;
    }

    public Integer getWindControlAbility() {
        return windControlAbility;
    }

    public void setWindControlAbility(Integer windControlAbility) {
        this.windControlAbility = windControlAbility;
    }

    public Double getTimingCoefficient() {
        return timingCoefficient;
    }

    public void setTimingCoefficient(Double timingCoefficient) {
        this.timingCoefficient = timingCoefficient;
    }

    public Integer getMarketTimingAbility() {
        return marketTimingAbility;
    }

    public void setMarketTimingAbility(Integer marketTimingAbility) {
        this.marketTimingAbility = marketTimingAbility;
    }

    public Double getStockSelectionCoefficient() {
        return stockSelectionCoefficient;
    }

    public void setStockSelectionCoefficient(Double stockSelectionCoefficient) {
        this.stockSelectionCoefficient = stockSelectionCoefficient;
    }

    public Integer getStockOptionAbility() {
        return stockOptionAbility;
    }

    public void setStockOptionAbility(Integer stockOptionAbility) {
        this.stockOptionAbility = stockOptionAbility;
    }

    public Double getWeightedTenureLength() {
        return weightedTenureLength;
    }

    public void setWeightedTenureLength(Double weightedTenureLength) {
        this.weightedTenureLength = weightedTenureLength;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Double getAverageAbility() {
        return averageAbility;
    }

    public void setAverageAbility(Double averageAbility) {
        this.averageAbility = averageAbility;
    }

    private Double stockSelectionCoefficient;//择股系数

    private Integer stockOptionAbility;//择股能力

    private Double weightedTenureLength;//加权任职时长

    private Integer experience;//经验值

    private Double averageAbility;//平均能力




}
