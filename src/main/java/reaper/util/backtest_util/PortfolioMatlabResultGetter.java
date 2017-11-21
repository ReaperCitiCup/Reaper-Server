package reaper.util.backtest_util;

import Asset_Allocation.Asset_Allocation;
import Asset_Allocation_Factor.Asset_Allocation_Factor;
import Barra.Barra;
import com.mathworks.toolbox.javabuilder.MWArray;
import com.mathworks.toolbox.javabuilder.MWCharArray;
import com.mathworks.toolbox.javabuilder.MWException;
import com.mathworks.toolbox.javabuilder.MWNumericArray;
import org.apache.tomcat.util.buf.StringUtils;
import reaper.bean.BarraFactorBean;

import java.util.*;

/**
 * @author keenan on 01/11/2017
 */
@SuppressWarnings("Duplicates")
public class PortfolioMatlabResultGetter {
    /**
     * 创建组合时获得各基金的权重 matlab调用
     *
     * @param codes         基金代码
     * @param portfolioType 分散化方法类型
     * @return <基金代码, 权重(未百分化的double)>
     */
    public static Map<String, Double> calComponentWeight(List<String> codes, int portfolioType, List<Double> input_kind, List<Double> input_weight, int uncentralize_type, double profitRate) {
        Map<String, Double> resultMap = new HashMap<>();
        List<String> sorted = new ArrayList<>(codes);
        Collections.sort(sorted);
        String[] strings = codes.toArray(new String[codes.size()]);
        Double[] input_kind_array = input_kind.toArray(new Double[input_kind.size()]);

        Double[] input_weight_array = input_weight.toArray(new Double[input_weight.size()]);

        /**
         * 资产间分散需要的参数
         *
         *  String[] codes = {"000005","150039","002853","519746","150197","000007"};
         *  double[] input_kind={1,1,1,2,2,2};//输入资产的种类，1代表债券型基金，2代表股票型基金，3代表混合型
         *  double[] input_weight={0.7,0.3,0};//大类资产配置的比例，债券型0.7，股票型0.3，混合型0

         *  输入input_kind的长度必须和codes对应，input_weight(m3)指
         *  大类资产比例（0.7指所有债券基金配置比例和占70%，0.3指股票型基金配置比例和占30%）
         *  这个参数应该是前面选择时保留下来的，如果配置比例是0.6,0.4,0，就相应调整。
         *  另外，codes,input_kind与input_weight是互相对应，上面的初始化表示"000005","150039","002853"
         *  这三支基金是债券型基金，其配置的比例和为0.7，"519746","150197","000007"是股票型基金，其
         *  配置的比例之和为0.3。
         */
        if (uncentralize_type == 1) {
            Object[] result = null;
            Asset_Allocation assetAllocation = null;
            MWCharArray funds = null;
            MWNumericArray pType = null;
            MWNumericArray inputKind = null;
            MWNumericArray inputWeight = null;

            try {
                funds = new MWCharArray(strings);
                pType = new MWNumericArray(portfolioType);
                inputKind = new MWNumericArray(input_kind_array);
                inputWeight = new MWNumericArray(input_weight_array);
                assetAllocation = new Asset_Allocation();
                if (portfolioType == 2) {
                    result = assetAllocation.asset_arrangement(1, funds, pType, inputKind, inputWeight, new MWNumericArray(profitRate));
                } else {
                    result = assetAllocation.asset_arrangement(1, funds, pType, inputKind, inputWeight);
                }
                String[] res = null;
                if (result != null && result.length != 0) {
                    System.out.println(result[0].toString());
                    res = result[0].toString().split("\n");
                } else {
                    return Collections.EMPTY_MAP;
                }

                if (res == null || res.length != codes.size()) {
                    return Collections.EMPTY_MAP;
                }

                for (int i = 0; i < sorted.size(); i++) {
                    resultMap.put(sorted.get(i), Double.valueOf(res[i]));
                    System.out.println(sorted.get(i) + "\t\t" + Double.valueOf(res[i]));
                }

                return resultMap;
            } catch (MWException e) {
                return Collections.EMPTY_MAP;
            } finally {
                MWArray.disposeArray(result);
                MWArray.disposeArray(funds);
                MWArray.disposeArray(pType);
                MWArray.disposeArray(inputKind);
                MWArray.disposeArray(inputWeight);

                if (assetAllocation != null) {
                    assetAllocation.dispose();
                    assetAllocation = null;
                }
            }
            /**
             * 因子间分散需要的参数
             *
             *  String[] codes = {"000007","000004","000017","000024","000025","000026","000052","000027","000065","000050","000003","000039","000042"};
             *  double[] input_kind={1,1,1,2,2,8,8,5,5,6,4,4,4};//输入因子的种类
             *  输入input_kind的长度必须和codes对应，input_facotr_num是指因子个数，input_kind指因子种类，其他
             *  变化不大，对参数格式有疑问可以问李振安
             */
        } else if (uncentralize_type == 2) {
            Object[] result = null;
            Asset_Allocation_Factor asset_allocation_factor = null;
            MWCharArray funds = null;
            MWNumericArray pType = null;
            MWNumericArray inputKind = null;
            MWNumericArray inputFactorNum = null;
            int input_factor_num = new HashSet<>(input_kind).size();

            try {
                asset_allocation_factor = new Asset_Allocation_Factor();
                funds = new MWCharArray(strings);
                pType = new MWNumericArray(portfolioType);
                inputKind = new MWNumericArray(input_kind_array);
                inputFactorNum = new MWNumericArray(input_factor_num);

                if (portfolioType == 2) {
                    result = asset_allocation_factor.factor_arrangement(1, funds, pType, inputKind, inputFactorNum, new MWNumericArray(profitRate));
                } else {
                    result = asset_allocation_factor.factor_arrangement(1, funds, pType, inputKind, inputFactorNum);
                }
                String[] res = null;
                if (result != null && result.length != 0) {
                    res = result[0].toString().split("\n");
                } else {
                    return Collections.EMPTY_MAP;
                }

                if (res == null || res.length != codes.size()) {
                    return Collections.EMPTY_MAP;
                }

                for (int i = 0; i < sorted.size(); i++) {
                    resultMap.put(sorted.get(i), Double.valueOf(res[i]));
//                    System.out.println(sorted.get(i) + "\t\t" + Double.valueOf(res[i]));
                }

                return resultMap;
            } catch (MWException e) {
                return Collections.EMPTY_MAP;
            } finally {
                MWArray.disposeArray(result);
                MWArray.disposeArray(funds);
                MWArray.disposeArray(pType);
                MWArray.disposeArray(inputKind);
                MWArray.disposeArray(inputFactorNum);

                if (asset_allocation_factor != null) {
                    asset_allocation_factor.dispose();
                    asset_allocation_factor = null;
                }
            }
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    public static Map<String, Double> getBarra(List<BarraFactorBean> barraFactorBeans) {
        Map<String, Double> resultMap = new HashMap<>();
        Object[] result = null;
        MWCharArray n = null;
        MWNumericArray m = null;
        Barra barra;

        try {
            double[] inputFactor = new double[barraFactorBeans.size()];
            String[] sql = new String[barraFactorBeans.size() + 2];
            sql[0] = "code";
            sql[sql.length - 1] = "annualProfit";

            for (int i = 0; i < barraFactorBeans.size(); i++) {
                inputFactor[i] = barraFactorBeans.get(i).value;
                sql[i + 1] = barraFactorBeans.get(i).name;
            }

            String s = StringUtils.join(Arrays.asList(sql), ',');

            n = new MWCharArray(s);
            m = new MWNumericArray(inputFactor);
            barra = new Barra();
            result = barra.barra(2, n, m);

            String[] codes;
            String[] percent;

            if (result != null && result.length == 2) {
                codes = result[0].toString().split("\n");
                percent = result[1].toString().split("\n");

                for (int i = 0; i < codes.length; i++) {
                    resultMap.put(codes[i], Double.valueOf(percent[i]) * 100);
                }
            }

            return resultMap;
        } catch (MWException e) {
            e.printStackTrace();
            return new HashMap<>();
        } finally {
            MWArray.disposeArray(n);
            MWArray.disposeArray(m);
            MWArray.disposeArray(result);
        }

    }
}
