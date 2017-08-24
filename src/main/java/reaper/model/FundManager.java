package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fund_manager")
public class FundManager {
    @Id
    @GeneratedValue
    private Integer id;

    private String fundCode;

    private String managerId;

    public FundManager(){

    }

    @Column(length = 6)
    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    @Column(length = 8)
    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

}
