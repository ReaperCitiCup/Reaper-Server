package reaper.model;

import javax.persistence.*;

/**
 * Created by max on 2017/8/26.
 */

@Entity
@Table(name = "industry_rate")


public class IndustryRate {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 6)
    private String code;
    @Column(length = 64)
    private String Industry;
    private Double cyl;
    private Double zjzb;
    private Double hyltz;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public Double getCyl() {
        return cyl;
    }

    public void setCyl(Double cyl) {
        this.cyl = cyl;
    }

    public Double getZjzb() {
        return zjzb;
    }

    public void setZjzb(Double zjzb) {
        this.zjzb = zjzb;
    }

    public Double getHyltz() {
        return hyltz;
    }

    public void setHyltz(Double hyltz) {
        this.hyltz = hyltz;
    }




}
