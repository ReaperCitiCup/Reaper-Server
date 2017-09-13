package reaper.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by max on 2017/9/13.
 */

@Entity
@Table(name = "fund_remark")
public class FundRemark {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getPositive() {
        return positive;
    }

    public void setPositive(Integer positive) {
        this.positive = positive;
    }

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    private Date startDate;

    private Integer positive;

    private Integer negative;


}
