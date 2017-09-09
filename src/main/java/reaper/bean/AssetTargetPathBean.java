package reaper.bean;

/**
 * 资产配置-目标+路径
 *
 * @author keenan on 09/09/2017
 */
public class AssetTargetPathBean {
    /**
     * 1=低 2=中 3=高
     */
    public AssetTargetBean target;

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

    public AssetTargetPathBean(AssetTargetBean target, int path, AssetWeightBean weight, AssetCategoryBean category) {
        this.target = target;
        this.path = path;
        this.weight = weight;
        this.category = category;
    }

    public AssetTargetPathBean() {
    }
}
