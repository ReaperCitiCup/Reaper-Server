package reaper.bean;

import reaper.model.Fund;
import reaper.model.Manager;
import reaper.util.FormatData;

/**
 * Created by Feng on 2017/9/10.
 */
public class PerformanceDataBean {
    /**
     * 基金代码 or 经理id
     */
    public String id;

    /**
     * 基金名称 or 经理姓名
     */
    public String name;

    /**
     * 收益率
     */
    public Double rate;

    /**
     * 风险
     */
    public Double risk;

    public PerformanceDataBean(String id, String name, Double rate, Double risk) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.risk = risk;
    }

    public PerformanceDataBean(Fund fund) {
        id = fund.getCode();
        name = fund.getName();
        rate = FormatData.fixToTwo(fund.getAnnualProfit());
        risk = FormatData.fixToTwo(fund.getVolatility());
    }

    public PerformanceDataBean(Manager manager) {
        id = manager.getManagerId();
        name = manager.getName();
        rate = FormatData.fixToTwoAndPercent(manager.getBestReturns());
        risk = FormatData.fixToTwoAndPercent(manager.getRisk());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerformanceDataBean that = (PerformanceDataBean) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
