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
    public Double stock;

    /**
     * 债券型基金
     */
    public Double bond;

    /**
     * 混合型基金
     */
    public Double hybrid;

    public AssetWeightBean(Double stock, Double bond, Double hybrid) {
        this.stock = stock;
        this.bond = bond;
        this.hybrid = hybrid;
    }

    public AssetWeightBean() {
    }

    @Override
    public String toString() {
        return "AssetWeightBean{" +
                "stock=" + stock +
                ", bond=" + bond +
                ", hybrid=" + hybrid +
                '}';
    }
}
