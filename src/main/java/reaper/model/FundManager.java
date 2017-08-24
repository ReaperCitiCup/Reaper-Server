package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fund_manager")
public class FundManager {
    @Id
    @GeneratedValue
    private Integer id;

    private String fundId;

    private String managerId;



    public FundManager(){

    }

    @Column(length = 6)
    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    @Column(length = 8)
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }


}
