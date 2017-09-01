package reaper.bean;

import java.util.List;

/**
 * 基金列表项Bean
 */
public class FundMiniBean {
    public String code;

    public String name;

    /**
     * 年化率
     */
    public Double annualProfit;

    /**
     * 波动率
     */
    public Double volatility;

    public List<MiniBean> manager;

    public FundMiniBean(String code, String name, Double annualProfit, Double volatility, List<MiniBean> manager) {
        this.code = code;
        this.name = name;
        this.annualProfit = annualProfit;
        this.volatility = volatility;
        this.manager = manager;
    }
}
