package reaper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.bean.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author keenan on 14/09/2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class CombinationServiceTest {
    @Autowired
    private CombinationService combinationService;

    @Test
    public void createCombinationByUser() throws Exception {
        String name = "szs测试用的";
        List<FundRatioBean> funds = new ArrayList<>();
        funds.add(new FundRatioBean("000022", 0.23));
        funds.add(new FundRatioBean("000067", 0.12));
        funds.add(new FundRatioBean("000069", 0.09));
        funds.add(new FundRatioBean("000075", 0.18));
        funds.add(new FundRatioBean("000085", 0.04));
        funds.add(new FundRatioBean("000091", 0.34));

    }

    @Test
    public void findCombinations() throws Exception {
    }

    @Test
    public void deleteCombination() throws Exception {
    }

    @Test
    public void backtestCombination() throws Exception {
        Integer combinationId = 3;
        String startDate = "2016-07-10";
        String endDtae = "2016-09-26";
        String baseIndex = "国债指数";


        BacktestReportBean reportBean = combinationService.backtestCombination(combinationId, startDate, endDtae, baseIndex);
        if (reportBean == null) {
            System.out.println("null");
        } else {
            System.out.println(reportBean.toString());
        }
    }

    @Test
    public void findFundsByTargetAndPath() throws Exception {
        AssetTargetPathBean assetTargetPathBean = new AssetTargetPathBean();
        assetTargetPathBean.profitRiskTarget = 5;
        assetTargetPathBean.path = 2;
        List<String> factors = new ArrayList<>();
//        factors.add("beta");
//        factors.add("btop");

        factors.add("momentum");
        factors.add("liquidity");
        assetTargetPathBean.factor = factors;

        List<CategoryFundBean> result = combinationService.findFundsByTargetAndPath(assetTargetPathBean);
        for (CategoryFundBean categoryFundBean : result) {
            System.out.println(categoryFundBean.name);
            for (MiniBean miniBean : categoryFundBean.funds) {
                System.out.println("\t" + miniBean.code + " " + miniBean.name);
            }
        }
//
//        /**
//         * 1 beta beta
//         * 2 btop 价值
//         * 3 盈利能力 profit
//         * 4 growth 成长性
//         * 5 leverage 杠杆率
//         * 6 liquidity 流动性
//         * 7 momentum 动量
//         * 8 nlsize 非线性市值
//         * 9 residualvolatility 波动率
//         * 10 size 市值
//         */

//        AssetTargetPathBean assetTargetPathBean = new AssetTargetPathBean();
//        assetTargetPathBean.profitRiskTarget = 4;
//        assetTargetPathBean.path = 1;
//        AssetWeightBean assetWeightBean = new AssetWeightBean();
//        assetWeightBean.bond = 0.3;
//        assetWeightBean.stock = 0.7;
//        assetWeightBean.stock = 0.0;
//        assetTargetPathBean.weight = assetWeightBean;
//
//        List<CategoryFundBean> result = combinationService.findFundsByTargetAndPath(assetTargetPathBean);
//        for (CategoryFundBean categoryFundBean : result) {
//            System.out.println(categoryFundBean.name);
//            for (MiniBean miniBean : categoryFundBean.funds) {
//                System.out.println("\t" + miniBean.code + " " + miniBean.name);
//            }
//        }
    }

    @Test
    public void createCombinationByAssetAllocation() throws Exception {
        FundCombinationBean combinationBean = new FundCombinationBean();

    }


}