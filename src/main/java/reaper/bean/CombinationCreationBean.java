package reaper.bean;

import java.util.List;

/**
 * @author keenan on 09/09/2017
 */
public class CombinationCreationBean {
    public String name;

    public List<FundRatioBean> funds;

    public CombinationCreationBean(String name, List<FundRatioBean> funds) {
        this.name = name;
        this.funds = funds;
    }

    public CombinationCreationBean() {
    }
}
