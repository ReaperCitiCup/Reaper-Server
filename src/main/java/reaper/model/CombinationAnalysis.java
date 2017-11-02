package reaper.model;

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
    public Integer profitRiskTarget;

    /**
     * 1=资产间分散, 2=因子间分散
     */
    @NotNull
    public Integer path;


    // 如果选择资产间分散 则 下三个权重 有效
    /**
     * 股票型基金
     */
    public Double stock;

    /**
     * 债券型基金
     */
    public Double bond;

    /**
     * 混合型基金
     */
    public Double hybrid;

    /**
     * 如果选择因子间分散，则factor有效
     * |分割
     */
    public String factor;

    /**
     * 组合id
     */
    @Id
    public Integer id;

    /**
     * 分类
     */
    public String category;

    /**
     * 选择的基金
     * |分割
     */
    public String codes;

    /**
     * 分散化方法 1 2 3
     */
    public Integer method;

    /**
     * 如果分散化方法为均值方差 2，则 profitRate 有效
     */
    public Double profitRate;

    public CombinationAnalysis() {
    }
}
