package reaper.bean;

import java.util.List;

/**
 * @author keenan on 11/09/2017
 */
public class BacktestComparisonBean {
    public List<ValueDateBean> fund;

    public List<ValueDateBean> base;

    public BacktestComparisonBean(List<ValueDateBean> fund, List<ValueDateBean> base) {
        this.fund = fund;
        this.base = base;
    }

    public BacktestComparisonBean() {
    }
}
