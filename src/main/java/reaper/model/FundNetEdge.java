package reaper.model;

import javax.persistence.*;

/**
 * Created by max on 2017/9/2.
 */
@Entity
@Table(name = "fund_net_edge")
public class FundNetEdge {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 6)
    private String codeIdA;
    @Column(length = 6)
    private String codeIdB;

    private double weight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeIdA() {
        return codeIdA;
    }

    public void setCodeIdA(String codeIdA) {
        this.codeIdA = codeIdA;
    }

    public String getCodeIdB() {
        return codeIdB;
    }

    public void setCodeIdB(String codeIdB) {
        this.codeIdB = codeIdB;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "FundNetEdge{" +
                "codeIdA='" + codeIdA + '\'' +
                ", codeIdB='" + codeIdB + '\'' +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FundNetEdge that = (FundNetEdge) o;

        if (Double.compare(that.weight, weight) != 0) return false;
        if (!codeIdA.equals(that.codeIdA)) return false;
        return codeIdB.equals(that.codeIdB);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = codeIdA.hashCode();
        result = 31 * result + codeIdB.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
