package reaper.util;

/**
 * 建立因子与序号间的映射关系
 * 1 beta beta
 * 2 btop 价值
 * 3 盈利能力 profit
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
    private FactorNumberMapping() {
    }

    public static Double factorThirtyNineName2No(String name) {
        if (name.equals("beta")) {
            return 1.0;
        } else if (name.equals("btop")) {
            return 2.0;
        } else if (name.equals("earningsyield")) {
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
        } else if (name.equals("residualvolatility")) {
            return 9.0;
        } else if (name.equals("size")) {
            return 10.0;
        } else if (name.equals("jx")) {
            return 11.0;
        } else if (name.equals("gfjg")) {
            return 12.0;
        } else if (name.equals("growth")) {
            return 13.0;
        } else if (name.equals("yh")) {
            return 14.0;
        } else if (name.equals("fdc")) {
            return 15.0;
        } else if (name.equals("yy")) {
            return 16.0;
        } else if (name.equals("cyly")) {
            return 17.0;
        } else if (name.equals("smls")) {
            return 18.0;
        } else if (name.equals("jc")) {
            return 19.0;
        } else if (name.equals("jd")) {
            return 20.0;
        } else if (name.equals("fzfz")) {
            return 21.0;
        } else if (name.equals("spyl")) {
            return 22.0;
        } else if (name.equals("dzyqj")) {
            return 23.0;
        } else if (name.equals("jtys")) {
            return 24.0;
        } else if (name.equals("qc")) {
            return 25.0;
        } else if (name.equals("qgzz")) {
            return 26.0;
        } else if (name.equals("dljgysy")) {
            return 27.0;
        } else if (name.equals("zh")) {
            return 28.0;
        } else if (name.equals("tx")) {
            return 29.0;
        } else if (name.equals("sysh")) {
            return 30.0;
        } else if (name.equals("ysjs")) {
            return 31.0;
        } else if (name.equals("nlmy")) {
            return 32.0;
        } else if (name.equals("jz")) {
            return 33.0;
        } else if (name.equals("jsj")) {
            return 34.0;
        } else if (name.equals("jchg")) {
            return 35.0;
        } else if (name.equals("mt")) {
            return 36.0;
        } else if (name.equals("dlsb")) {
            return 37.0;
        } else if (name.equals("gt")) {
            return 38.0;
        } else if (name.equals("")) {
            return 39.0;
        } else {
            return 0.0;
        }
    }

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
        if (fundType.equals("bond")) {
            return 1.0;
        } else if (fundType.equals("stock")) {
            return 2.0;
        } else if (fundType.equals("hybrid")) {
            return 3.0;
        } else {
            return 0.0;
        }
    }

    public static String no2FundType(Double no) {
        if (no.equals(1.0)) {
            return "bond";
        } else if (no.equals(2.0)) {
            return "stock";
        } else if (no.equals(3.0)) {
            return "hybrid";
        } else {
            return "";
        }
    }

    public static String baseIndexMapping(String eng) {
        if (eng.equals("szzs")) {
            return "上证指数";
        } else if (eng.equals("sz180")) {
            return "上证180";
        } else if (eng.equals("sz50")) {
            return "上证50";
        } else if (eng.equals("zz500")) {
            return "中正500";
        } else {
            return "国债指数";
        }
    }


}
