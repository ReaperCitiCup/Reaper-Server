package reaper.bean;

import java.util.List;

/**
 * 资产配置-目标+路径
 *
 * @author keenan on 09/09/2017
 */
public class AssetTargetPathBean {
    /**
     * 1-10
     */
    public Integer profitRiskTarget;

    /**
     * 1=资产间分散, 2=策略间分散 , 3=因子间分散
     */
    public int path;

    /**
     * 如果选择资产间分散 则 weight 有效
     */
    public AssetWeightBean weight;

    /**
     * 如果选择策略间分散 则 category 有效
     */
    public AssetCategoryBean category;

    /**
     * 如果选择因子间分散，则factor有效
     */
    public List<String> factor;

    public AssetTargetPathBean(Integer profitRiskTarget, int path, AssetWeightBean weight, AssetCategoryBean category, List<String> factor) {
        this.profitRiskTarget = profitRiskTarget;
        this.path = path;
        this.weight = weight;
        this.category = category;
        this.factor = factor;
    }

    public AssetTargetPathBean() {
    }
}
