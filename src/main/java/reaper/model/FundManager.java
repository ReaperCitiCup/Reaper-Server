package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fund_manager")
public class FundManager {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String fundCode;

    @Column(length = 8)
    private String managerId;

    public FundManager(){

    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

}
