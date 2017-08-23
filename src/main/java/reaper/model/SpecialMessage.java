package reaper.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by max on 2017/8/21.
 */
@Entity
@Table(name = "special_message")
public class SpecialMessage {
    @Id
    @GeneratedValue
    private Integer id;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    private Double standardDeviation1;

    public SpecialMessage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getStandardDeviation1() {
        return standardDeviation1;
    }

    public void setStandardDeviation1(Double standardDeviation1) {
        this.standardDeviation1 = standardDeviation1;
    }

    public Double getStandardDeviation2() {
        return standardDeviation2;
    }

    public void setStandardDeviation2(Double standardDeviation2) {
        this.standardDeviation2 = standardDeviation2;
    }

    public Double getStandardDeviation3() {
        return standardDeviation3;
    }

    public void setStandardDeviation3(Double standardDeviation3) {
        this.standardDeviation3 = standardDeviation3;
    }

    public Double getSharpeRatio1() {
        return sharpeRatio1;
    }

    public void setSharpeRatio1(Double sharpeRatio1) {
        this.sharpeRatio1 = sharpeRatio1;
    }

    public Double getSharpeRatio2() {
        return sharpeRatio2;
    }

    public void setSharpeRatio2(Double sharpeRatio2) {
        this.sharpeRatio2 = sharpeRatio2;
    }

    public Double getSharpeRatio3() {
        return sharpeRatio3;
    }

    public void setSharpeRatio3(Double sharpeRatio3) {
        this.sharpeRatio3 = sharpeRatio3;
    }

    private Double standardDeviation2;
    private Double standardDeviation3;
    private Double sharpeRatio1;
    private Double sharpeRatio2;
    private Double sharpeRatio3;









}
