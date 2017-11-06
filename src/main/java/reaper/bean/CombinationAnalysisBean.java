package reaper.bean;

import reaper.model.CombinationAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationAnalysisBean {
    /**
     * 1-10
     */
    public Integer profitRiskTarget;

    /**
     * 1=资产间分散, 2=因子间分散
     */
    public Integer path;

    /**
     * 如果选择资产间分散 则 weight 有效
     */
    public AssetWeightBean weight;

    /**
     * 如果选择因子间分散，则factor有效
     */
    public List<String> factor;

    /**
     * 选择的基金，需要分类
     */
    public List<FundCategoryBean> funds;

    /**
     * 分散化方法 1 2 3
     */
    public Integer method;

    /**
     * 如果分散化方法为均值方差 2，则 profitRate 有效
     */
    public Double profitRate;

    public CombinationAnalysisBean(CombinationAnalysis entity) {
        profitRate = entity.getProfitRate();
        path = entity.getPath();
        weight = new AssetWeightBean(entity.getStock(), entity.getBond(), entity.getHybrid());
        //此处使用aslist，数组固定大小，不支持add
        factor = Arrays.asList(entity.getFactor().split("\\|"));
        funds = new ArrayList<FundCategoryBean>() {{
            add(new FundCategoryBean("股票型基金", Arrays.asList(entity.getStockCodes().split("\\|"))));
            add(new FundCategoryBean("债券型基金", Arrays.asList(entity.getBondCodes().split("\\|"))));
            add(new FundCategoryBean("混合型基金", Arrays.asList(entity.getHybridCodes().split("\\|"))));
        }};

        method = entity.getMethod();
        profitRate = entity.getProfitRate();

    }
}
