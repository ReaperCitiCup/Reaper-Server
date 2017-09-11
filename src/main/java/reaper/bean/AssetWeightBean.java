package reaper.bean;

/**
 * 如果选择资产间分散 则 weight 有效
 *
 * @author keenan on 09/09/2017
 */
public class AssetWeightBean {
    /**
     * 股票型基金
     */
    public Integer stock;

    /**
     * 债券型基金
     */
    public Integer bond;

    /**
     * 混合型基金
     */
    public Integer hybrid;

    public AssetWeightBean(Integer stock, Integer bond, Integer hybrid) {
        this.stock = stock;
        this.bond = bond;
        this.hybrid = hybrid;
    }

    public AssetWeightBean() {
    }
}
