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
    @Column(length = 8)
    private String managerIdA;
    @Column(length = 8)
    private String managerIdB;

    private int times;

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

    private int  days;









}
