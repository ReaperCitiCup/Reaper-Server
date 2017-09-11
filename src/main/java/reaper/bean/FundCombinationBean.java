package reaper.bean;

import java.util.List;

/**
 * 资产配置-基金+组合
 *
 * @author keenan on 09/09/2017
 */
public class FundCombinationBean {
    public String name;

    /**
     * 最多为10个
     */
    public List<String> funds;

    public int method;

    public FundCombinationBean(String name, List<String> funds, int method) {
        this.name = name;
        this.funds = funds;
        this.method = method;
    }

    public FundCombinationBean() {
    }
}
