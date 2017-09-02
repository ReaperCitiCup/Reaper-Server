package reaper.model;

import javax.persistence.*;

/**
 * Created by max on 2017/9/2.
 */
@Entity
@Table(name = "manager_relation")
public class ManagerRelation {
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
}
