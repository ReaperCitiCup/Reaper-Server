package reaper.bean;

/**
 * Created by Feng on 2017/8/23.
 */
public class ManagerBean {
    /**
     * 经理id
     */
    public String id;

    /**
     * 经理姓名
     */
    public String name;

    /**
     *爬不到gender的数据
     */
    public String gender;

    /**
     * 任命时间
     */
    public String appointedDate;

    /**
     * 所在公司
     */
    public CompanyMiniBean company;

    /**
     * 总基金
     */
    public Double totalScope;

    /**
     * 最佳任期回报
     */
    public Double bestReturns;

    /**
     * 介绍
     */
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
