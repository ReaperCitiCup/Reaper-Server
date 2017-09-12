package reaper.util;

/**
 * 建立因子与序号间的映射关系
 * 1 beta beta
 * 2 btop 价值
 * 3 earningsyield 盈利能力 profit
 * 4 growth 成长性
 * 5 leverage 杠杆率
 * 6 liquidity 流动性
 * 7 momentum 动量
 * 8 nlsize 非线性市值
 * 9 residualvolatility 波动率
 * 10 size 市值
 * <p>
 * 1 代表债券型基金，2 代表股票型基金，3 代表混合型
 *
 * @author keenan on 12/09/2017
 */
public class FactorNumberMapping {
    public static Double factorName2No(String name) {
        //beta; btop; profit; growth; leverage;
        //liquidity; momentum; nlsize; volatility; size;
        if (name.equals("beta")) {
            return 1.0;
        } else if (name.equals("btop")) {
            return 2.0;
        } else if (name.equals("profit")) {
            return 3.0;
        } else if (name.equals("growth")) {
            return 4.0;
        } else if (name.equals("leverage")) {
            return 5.0;
        } else if (name.equals("liquidity")) {
            return 6.0;
        } else if (name.equals("momentum")) {
            return 7.0;
        } else if (name.equals("nlsize")) {
            return 8.0;
        } else if (name.equals("volatility")) {
            return 9.0;
        } else if (name.equals("size")) {
            return 10.0;
        } else {
            return 0.0;
        }
    }

    public static String no2FactorName(Double no) {
        if (no.equals(1.0)) {
            return "beta";
        } else if (no.equals(2.0)) {
            return "btop";
        } else if (no.equals(3.0)) {
            return "profit";
        } else if (no.equals(4.0)) {
            return "growth";
        } else if (no.equals(5.0)) {
            return "leverage";
        } else if (no.equals(6.0)) {
            return "liquidity";
        } else if (no.equals(7.0)) {
            return "momentum";
        } else if (no.equals(8.0)) {
            return "nlsize";
        } else if (no.equals(9.0)) {
            return "volatility";
        } else if (no.equals(10.0)) {
            return "size";
        } else return "";
    }

    public static Double fundType2No(String fundType) {
        if (fundType.equals("债券型")) {
            return 1.0;
        } else if (fundType.equals("股票型")) {
            return 2.0;
        } else if (fundType.equals("混合型")) {
            return 3.0;
        } else {
            return 0.0;
        }
    }

    public static String no2FundType(Double no) {
        if (no.equals(1.0)) {
            return "债券型";
        } else if (no.equals(2.0)) {
            return "股票型";
        } else if (no.equals(3.0)) {
            return "混合型";
        } else {
            return "";
        }
    }


}
