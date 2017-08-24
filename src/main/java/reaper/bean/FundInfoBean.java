package reaper.bean;

import reaper.model.Fund;

import java.util.List;

/**
 * 基金详细信息
 */
public class FundInfoBean {
    /**
     * 基金代码
     */
    public String code;

    /**
     * 基金名称
     */
    public String name;

    /**
     * 类型
     */
    public String[] type;

    /**
     * 建立日期
     */
    public String establishmentDate;

    /**
     * 基金规模
     */
    public double scope;

    /**
     * 单位净值
     */
    public double unitNetValue;

    /**
     * 积累净值
     */
    public double cumulativeNetValue;

    /**
     * 日收益率
     */
    public double dailyRate;

    /**
     * 增长率
     */
    public RateBean rate;

    /**
     * 经理信息
     */
    public MiniBean manager;

    /**
     * 公司信息
     */
    public MiniBean company;

}
