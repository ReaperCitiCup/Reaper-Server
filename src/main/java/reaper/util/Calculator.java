package reaper.util;

import reaper.bean.ValueDateBean;

import java.util.List;

/**
 * @author samperson1997 created on 12/09/2017
 */
public class Calculator {

    public static Double calStandardDeviation(List<ValueDateBean> nums) {
        double sum = 0.0;
        double sum2 = 0.0;
        for (ValueDateBean num : nums) {
            sum += num.value;
            sum2 += num.value * num.value;
        }
        return (sum2 / nums.size() - (sum / nums.size()) * (sum / nums.size()));
    }
}
