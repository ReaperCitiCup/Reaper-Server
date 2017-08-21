package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "fund_company")
public class FundCompany {
    @Id
    @GeneratedValue
    private Integer id;

    private String fundId;

    private String companyId;

    public FundCompany(){

    }

    @Column(length = 6)
    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    @Column(length = 8)
    public String getcompanyId() {
        return companyId;
    }

    public void setcompanyId(String companyId) {
        this.companyId = companyId;
    }
}
