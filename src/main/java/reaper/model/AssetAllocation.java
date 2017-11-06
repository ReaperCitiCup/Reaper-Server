package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "asset_allocation")
public class AssetAllocation {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6,unique = true,nullable = false)
    private String code;

    /**
     * 股票
     */
    private Double stock;

    /**
     * 债券
     */
    private Double bond;

    /**
     * 银行存款
     */
    private Double bank;

    @Column(length = 8)
    private String companyId;

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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
