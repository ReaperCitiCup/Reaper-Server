package reaper.util.backtest_util;

import reaper.bean.ValueDateBean;
import reaper.util.FormatData;
import reaper.util.PythonUser;
import reaper.util.ValueDateBeanComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static reaper.util.CodeFormatUtil.combineAndFillBlank;

/**
 * @author keenan on 01/11/2017
 */
public class BackTestPyAnalysisGetter {
    private static final String FILE_BACK_ANALYSIS = "backtest.py";

    /**
     * 调用python代码 可得到年化收益率，年化波动率，在险价值，收益率序列的下行标准差，夏普比率，beta，特雷诺指数，择股系数，择时系数，相关系数
     *
     * @param codeList   代码
     * @param percentage 占比
     * @param startDate  开始日期
     * @param endDate    结束日期
     * @return
     */
    public static PyAnalysisResult getBasicFactors(List<String> codeList, List<Double> percentage, String startDate, String endDate) {
        PyAnalysisResult result = new PyAnalysisResult();
        String pyRes = PythonUser.usePy(FILE_BACK_ANALYSIS, startDate + " " + endDate + " " + combineAndFillBlank(codeList, percentage));

        System.out.println(pyRes);

        String[] lines = pyRes.split("\n");
        List<String> useful = new ArrayList<>();
        for (String line : lines) {
            if (line.startsWith("#")) {
                useful.add(line);
            }
        }

        List<PyAnalysisResult.CorrelationCoefficient> coefficients = new ArrayList<>();
        TreeSet<ValueDateBean> dailyReturnRates = new TreeSet<>(new ValueDateBeanComparator());
        TreeSet<ValueDateBean> cumNet = new TreeSet<>(new ValueDateBeanComparator());
        TreeSet<ValueDateBean> dailyRetrace = new TreeSet<>(new ValueDateBeanComparator());
        for (String each : useful) {
            String[] values = each.split(" ");
            if (values[1].equals("年化收益率=")) {
                result.setNhsyl(Double.valueOf(values[2]));
            } else if (values[1].equals("年化波动率=")) {
                result.setNhbdl(Double.valueOf(values[2]));
            } else if (values[1].equals("在险价值=")) {
                result.setZxjz(Double.valueOf(values[2]));
            } else if (values[1].equals("下行标准差=")) {
                result.setXxbzc(Double.valueOf(values[2]));
            } else if (values[1].equals("夏普比=")) {
                result.setSharpe(Double.valueOf(values[2]));
            } else if (values[1].equals("beta=")) {
                result.setBeta(Double.valueOf(values[2]));
            } else if (values[1].equals("特雷诺指数=")) {
                result.setTln(Double.valueOf(values[2]));
            } else if (values[1].equals("择股系数=")) {
                result.setZgxs(Double.valueOf(values[2]));
            } else if (values[1].equals("择时系数=")) {
                result.setZsxs(Double.valueOf(values[2]));
            } else if (values[1].equals("累计收益=")) {
                result.setLjsy(Double.valueOf(values[2]));
            } else if (values[1].equals("最大跌幅=")) {
                result.setZddf(Double.valueOf(values[2]));
            } else if (values[1].equals("期初净值=")) {
                result.setQcjz(Double.valueOf(values[2]));
            } else if (values[1].equals("期末净值=")) {
                result.setQmjz(Double.valueOf(values[2]));
            } else if (values[1].equals("累计净值=")) {
                cumNet.add(new ValueDateBean(values[2], Double.valueOf(values[3])));
            } else if (values[1].equals("日收益率=")) {
                dailyReturnRates.add(new ValueDateBean(values[2], FormatData.fixToTwoAndPercent(Double.valueOf(values[3]))));
            } else if (values[1].equals("每日回撤=")) {
                dailyRetrace.add(new ValueDateBean(values[2], Double.valueOf(values[3])));
            } else {
                PyAnalysisResult.CorrelationCoefficient correlationCoefficient = result.new CorrelationCoefficient(values[1], values[2], Double.valueOf(values[3]));
                coefficients.add(correlationCoefficient);
            }
        }

        result.setPjxgxs(coefficients);
        result.setCumNet(new ArrayList<>(cumNet));
        result.setDailyRetrace(new ArrayList<>(dailyRetrace));
        result.setDailyReturnRate(new ArrayList<>(dailyReturnRates));
        return result;
    }

    public static void main(String[] args) {
        List<String> codes = new ArrayList<>(Arrays.asList("000120", "000308"));
        List<Double> percentage = new ArrayList<>(Arrays.asList(50.0, 50.0));

        PyAnalysisResult result = getBasicFactors(codes, percentage, "1900-10-01", "2017-11-04");
    }
}
