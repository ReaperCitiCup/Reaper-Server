package reaper.model;

import javax.persistence.*;

@Entity
@Table(name = "fund_type_rank")
public class FundRankByType {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6)
    private String code;

    private String type;

    private Integer rank;

    private Integer total;

    private Double score;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
