package reaper.bean;

import reaper.util.FormatData;

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
        //若为债券型基金
        if(stock.equals(0.0)&&bank.equals(0.0)){
            this.bond = 100.0;
        }
        other = FormatData.fixToTwo(100-this.bank-this.bond-this.stock);
    }
}
