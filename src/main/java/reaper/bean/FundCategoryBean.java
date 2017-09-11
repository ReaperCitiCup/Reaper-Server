package reaper.bean;

import java.util.List;

/**
 * @author keenan on 11/09/2017
 */
public class FundCategoryBean {
    public String category;

    public List<String> codes;

    public FundCategoryBean(String category, List<String> codes) {
        this.category = category;
        this.codes = codes;
    }

    public FundCategoryBean() {
    }
}
