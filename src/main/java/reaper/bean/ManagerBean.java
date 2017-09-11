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
     * 任命时间
     */
    public String appointedDate;

    /**
     * 累计时间
     */
    public String cumulativeDays;

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

    public String managerImageUrl;

    public ManagerBean(String id, String name, String appointedDate, String cumulativeDays, CompanyMiniBean company, Double totalScope, Double bestReturns, String introduction) {
        this.id = id;
        this.name = name;
        this.appointedDate = appointedDate;
        this.cumulativeDays = cumulativeDays;
        this.company = company;
        this.totalScope = totalScope;
        this.bestReturns = bestReturns;
        this.introduction = introduction;
        this.managerImageUrl="https://pdf.dfcfw.com/pdf/H8_JPG"+id+"_1.jpg";
    }
}
