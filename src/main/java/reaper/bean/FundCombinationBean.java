package reaper.bean;

import java.util.List;

/**
 * 资产配置-基金+组合
 *
 * @author keenan on 09/09/2017
 */
public class FundCombinationBean {
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

    /**
     * 组合的名称
     */
    public String name;

    /**
     * 选择的基金，需要分类
     */
    public FundCategoryBean funds;

    /**
     * 分散化方法 1 2 3
     */
    public int method;

    public FundCombinationBean(Integer profitRiskTarget, int path, AssetWeightBean weight, AssetCategoryBean category, List<String> factor, String name, FundCategoryBean funds, int method) {
        this.profitRiskTarget = profitRiskTarget;
        this.path = path;
        this.weight = weight;
        this.category = category;
        this.factor = factor;
        this.name = name;
        this.funds = funds;
        this.method = method;
    }

    public FundCombinationBean() {
    }
}
