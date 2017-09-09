package reaper.bean;

/**
 * 当前资产配置
 */
public class CurrentAssetBean {
    public Double bond;

    public Double stock;

    public Double bank;

    public Double other;

    public CurrentAssetBean(Double bond, Double stock, Double bank) {
        this.bond = bond;
        this.stock = stock;
        this.bank = bank;
        other = 100-bank-bond-stock;
    }
}
