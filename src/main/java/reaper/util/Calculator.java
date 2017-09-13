package reaper.util;

/**
 * @author samperson1997 created on 12/09/2017
 */
public class Calculator {

    public static Double calStandardDeviation(double[] nums) {
        double sum = 0.0;
        double sum2 = 0.0;
        for (double num : nums) {
            sum += num;
            sum2 += num * num;
        }
        return (sum2 / nums.length - (sum / nums.length) * (sum / nums.length));
    }
}
