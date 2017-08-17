package reaper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "fund_netValue")
public class FundNetValue {
    @Id
    @GeneratedValue
    private Integer id;

    private Date date;

    private Double unitNetValue;

    private Double cumulativeNetValue;

    private Double dailyRate;

    public FundNetValue(){

    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getUnitNetValue() {
        return unitNetValue;
    }

    public void setUnitNetValue(Double unitNetValue) {
        this.unitNetValue = unitNetValue;
    }

    public Double getCumulativeNetValue() {
        return cumulativeNetValue;
    }

    public void setCumulativeNetValue(Double cumulativeNetValue) {
        this.cumulativeNetValue = cumulativeNetValue;
    }

    public Double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Double dailyRate) {
        this.dailyRate = dailyRate;
    }
}
