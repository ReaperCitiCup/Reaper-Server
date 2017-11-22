package reaper.model;

import org.apache.tomcat.util.buf.StringUtils;
import reaper.bean.FundCategoryBean;
import reaper.bean.FundCombinationBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "combination_analysis")
public class CombinationAnalysis {
    /**
     * 1-10
     */
    @NotNull
    private Integer profitRiskTarget;

    /**
     * 1=资产间分散, 2=因子间分散
     */
    @NotNull
    private Integer path;


    // 如果选择资产间分散 则 下三个权重 有效
    /**
     * 股票型基金
     */
    private Double stock;

    /**
     * 债券型基金
     */
    private Double bond;

    /**
     * 混合型基金
     */
    private Double hybrid;

    /**
     * 如果选择因子间分散，则factor有效
     * |分割
     */
    private String factor;

    /**
     * 组合id
     */
    @Id
    private Integer id;

    /**
     * 选择的基金的代码，分为3类
     * |分割
     */
    private String stockCodes;

    private String bondCodes;

    private String hybridCodes;

    /**
     * 分散化方法 1 2 3
     */
    private Integer method;

    /**
     * 如果分散化方法为均值方差 2，则 profitRate 有效
     */
    private Double profitRate;

    public CombinationAnalysis() {
    }

    public CombinationAnalysis(FundCombinationBean bean) {
        profitRiskTarget = bean.profitRiskTarget;
        profitRate = bean.profitRate;
        path = bean.path;
        stock = bean.weight==null?null:bean.weight.stock;
        bond = bean.weight==null?null:bean.weight.bond;
        hybrid = bean.weight==null?null:bean.weight.hybrid;
        factor = factor==null?null:StringUtils.join(bean.factor,'|');
        for(FundCategoryBean fundCategoryBean:bean.funds) {
            if(fundCategoryBean.category.equals("股票型基金")) {
                stockCodes = StringUtils.join(fundCategoryBean.codes,'|');
            }else if(fundCategoryBean.category.equals("债券型基金")) {
                bondCodes = StringUtils.join(fundCategoryBean.codes,'|');
            }else if(fundCategoryBean.category.equals("混合型基金")) {
                hybridCodes = StringUtils.join(fundCategoryBean.codes,'|');
            }
        }
        method = bean.method;
    }

    public Integer getProfitRiskTarget() {
        return profitRiskTarget;
    }

    public void setProfitRiskTarget(Integer profitRiskTarget) {
        this.profitRiskTarget = profitRiskTarget;
    }

    public Integer getPath() {
        return path;
    }

    public void setPath(Integer path) {
        this.path = path;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public Double getBond() {
        return bond;
    }

    public void setBond(Double bond) {
        this.bond = bond;
    }

    public Double getHybrid() {
        return hybrid;
    }

    public void setHybrid(Double hybrid) {
        this.hybrid = hybrid;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockCodes() {
        return stockCodes;
    }

    public void setStockCodes(String stockCodes) {
        this.stockCodes = stockCodes;
    }

    public String getBondCodes() {
        return bondCodes;
    }

    public void setBondCodes(String bondCodes) {
        this.bondCodes = bondCodes;
    }

    public String getHybridCodes() {
        return hybridCodes;
    }

    public void setHybridCodes(String hybridCodes) {
        this.hybridCodes = hybridCodes;
    }

    public Integer getMethod() {
        return method;
    }

    public void setMethod(Integer method) {
        this.method = method;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Double profitRate) {
        this.profitRate = profitRate;
    }
}
