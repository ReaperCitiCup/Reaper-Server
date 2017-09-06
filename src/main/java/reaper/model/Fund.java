package reaper.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fund")
public class Fund {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String code;

    @Column(length = 128)
    private String name;

    @Column(length = 32)
    private String type1;

    @Column(length = 32)
    private String type2;

    private Date establishmentDate;

    private Double scope;

    /**
     * 年化率
     */
    private Double annualProfit;

    /**
     * 波动率
     */
    private Double volatility;

    public Fund(){

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public Double getScope() {
        return scope;
    }

    public void setScope(Double scope) {
        this.scope = scope;
    }

    public Double getAnnualProfit() {
        return annualProfit;
    }

    public void setAnnualProfit(Double annualProfit) {
        this.annualProfit = annualProfit;
    }

    public Double getVolatility() {
        return volatility;
    }

    public void setVolatility(Double volatility) {
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", scope=" + scope +
                ", annualProfit=" + annualProfit +
                ", volatility=" + volatility +
                '}';
    }
}
