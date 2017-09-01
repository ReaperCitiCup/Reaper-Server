package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "asset_allocation")
public class AssetAllocation {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(length = 6)
    public String code;

    /**
     * 股票
     */
    public Double stock;

    /**
     * 债券
     */
    public Double bond;

    /**
     * 银行存款
     */
    public Double bank;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Double getBank() {
        return bank;
    }

    public void setBank(Double bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "AssetAllocation{" +
                "code='" + code + '\'' +
                ", stock=" + stock +
                ", bond=" + bond +
                ", bank=" + bank +
                '}';
    }
}
