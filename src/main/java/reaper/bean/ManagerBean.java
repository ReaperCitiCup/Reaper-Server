package reaper.bean;

/**
 * Created by Feng on 2017/8/23.
 */
public class ManagerBean {
    public String id;
    public String name;
    public String gender;
    public String appointedDate;
    public CompanyMiniBean company;
    public Double totalScope;
    public Double bestReturns;
    public String introduction;

    public ManagerBean(String id, String name, String appointedDate, CompanyMiniBean company, Double totalScope, Double bestReturns, String introduction) {
        this.id = id;
        this.name = name;
        this.appointedDate = appointedDate;
        this.company = company;
        this.totalScope = totalScope;
        this.bestReturns = bestReturns;
        this.introduction = introduction;
    }
}
