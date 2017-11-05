package reaper.model;

import javax.persistence.*;

/**
 * Created by max on 2017/9/11.
 */
@Entity
@Table(name = "manager_edge")
public class ManagerEdge {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 8,nullable = false)
    private String managerIdA;
    @Column(length = 8,nullable = false)
    private String managerIdB;
    @Column(length = 32,nullable = false)
    private String managerNameA;
    @Column(length = 32,nullable = false)
    private String managerNameB;
    @Column(nullable = false)
    private int times;
    @Column(nullable = false)
    private int  days;

    public String getManagerIdA() {
        return managerIdA;
    }

    public void setManagerIdA(String managerIdA) {
        this.managerIdA = managerIdA;
    }

    public String getManagerIdB() {
        return managerIdB;
    }

    public void setManagerIdB(String managerIdB) {
        this.managerIdB = managerIdB;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getManagerNameA() {
        return managerNameA;
    }

    public void setManagerNameA(String managerNameA) {
        this.managerNameA = managerNameA;
    }

    public String getManagerNameB() {
        return managerNameB;
    }

    public void setManagerNameB(String managerNameB) {
        this.managerNameB = managerNameB;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManagerEdge that = (ManagerEdge) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
