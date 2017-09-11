package reaper.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 基金各种方式排名的指标
 */
@Entity
@Table(name = "fund_rank")
public class FundRank {
    @Id
    @Column(length = 6)
    private String code;

    private Double rank1;
    private Double rank2;
    private Double rank3;
    private Double rank4;
    private Double rank5;
    private Double rank6;
    private Double rank7;
    private Double rank8;
    private Double rank9;
    private Double rank10;

    public Double getRank1() {
        return rank1;
    }

    public void setRank1(Double rank1) {
        this.rank1 = rank1;
    }

    public Double getRank2() {
        return rank2;
    }

    public void setRank2(Double rank2) {
        this.rank2 = rank2;
    }

    public Double getRank3() {
        return rank3;
    }

    public void setRank3(Double rank3) {
        this.rank3 = rank3;
    }

    public Double getRank4() {
        return rank4;
    }

    public void setRank4(Double rank4) {
        this.rank4 = rank4;
    }

    public Double getRank5() {
        return rank5;
    }

    public void setRank5(Double rank5) {
        this.rank5 = rank5;
    }

    public Double getRank6() {
        return rank6;
    }

    public void setRank6(Double rank6) {
        this.rank6 = rank6;
    }

    public Double getRank7() {
        return rank7;
    }

    public void setRank7(Double rank7) {
        this.rank7 = rank7;
    }

    public Double getRank8() {
        return rank8;
    }

    public void setRank8(Double rank8) {
        this.rank8 = rank8;
    }

    public Double getRank9() {
        return rank9;
    }

    public void setRank9(Double rank9) {
        this.rank9 = rank9;
    }

    public Double getRank10() {
        return rank10;
    }

    public void setRank10(Double rank10) {
        this.rank10 = rank10;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
