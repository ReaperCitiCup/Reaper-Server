package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "fund_company")
public class FundCompany {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String fundId;

    @Column(length = 8)
    private String managerId;

    public FundCompany(){

    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
