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
 *
 * @author keenan on 12/09/2017
 */
public class FactorNumberMapping {
    public static Double name2No(String name) {
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
}
