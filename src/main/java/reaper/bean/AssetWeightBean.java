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
    public int stock;

    /**
     * 债券型基金
     */
    public int bond;

    /**
     * 混合型基金
     */
    public int hybrid;

    public AssetWeightBean(int stock, int bond, int hybrid) {
        this.stock = stock;
        this.bond = bond;
        this.hybrid = hybrid;
    }

    public AssetWeightBean() {
    }
}
