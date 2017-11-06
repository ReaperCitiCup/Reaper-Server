package reaper.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reaper.bean.CategoryFundBean;
import reaper.bean.CombinationAnalysisBean;
import reaper.bean.FundCategoryBean;
import reaper.bean.MiniBean;
import reaper.model.Combination;
import reaper.model.CombinationAnalysis;
import reaper.model.Fund;
import reaper.repository.CombinationAnalysisRepository;
import reaper.repository.CombinationRepository;
import reaper.repository.FundRepository;
import reaper.util.FactorNumberMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static reaper.util.CodeFormatUtil.fillCode;
import static reaper.util.backtest_util.PortfolioTargetPathPythonGetter.getTargetPathCodes;

/**
 * 每天凌晨三点定时更新组合风险
 *
 * @author keenan on 01/11/2017
 */
@Component
@SuppressWarnings("Duplicates")
public class CombinationRiskScheduler {
    @Autowired
    private CombinationRepository combinationRepository;
    @Autowired
    private CombinationAnalysisRepository combinationAnalysisRepository;
    @Autowired
    private FundRepository fundRepository;

    @Scheduled(cron = "0 0 3 * * ?")
    public void updateRisk() throws Exception {
        List<CombinationAnalysis> combinationAnalyses = combinationAnalysisRepository.findAll();
        for (CombinationAnalysis combinationAnalysis : combinationAnalyses) {
            CombinationAnalysisBean combinationAnalysisBean = new CombinationAnalysisBean(combinationAnalysis);
            // 用户选择的基金，FundCategoryBean中只包含了类别和对应选择的代码
            List<FundCategoryBean> origin = combinationAnalysisBean.funds;
            // 根据条件重新计算的候选基金，CategoryFundBean中包含了类别和基金简要信息
            List<CategoryFundBean> current = findFundsByTargetAndPath(combinationAnalysisBean);
            if (!compareCategories(origin, current)) {
                Combination combination = combinationRepository.findOne(combinationAnalysis.getId());
                combination.setHasRisk(true);
                combinationRepository.save(combination);
            }
        }
    }

    /**
     * 资产配置-选择目标及路径
     *
     * @param combinationAnalysisBean
     * @return
     */
    private List<CategoryFundBean> findFundsByTargetAndPath(CombinationAnalysisBean combinationAnalysisBean) {
        Integer lamda = combinationAnalysisBean.profitRiskTarget;
        Integer path = combinationAnalysisBean.path;

        List<CategoryFundBean> result = new ArrayList<>();

        /**
         * 资产间分散
         */
        if (path == 1) {
            Integer count = 10;
            for (double i = 1.0; i <= 3.0; i += 1.0) {
                List<String> tmp = getTargetPathCodes(lamda, count, path, (int) i, 0);
                String cname = FactorNumberMapping.no2FundType(i);
                List<MiniBean> miniBeans = new ArrayList<>();
                for (String code : tmp) {
                    miniBeans.add(findFundNameByCode(code));
                }
                result.add(new CategoryFundBean(cname, miniBeans));
            }
            return result;
        }
        /**
         * 因子间分散
         */
        else if (path == 2) {
            Integer count = 3;
            for (String s : combinationAnalysisBean.factor) {
                List<String> tmp = getTargetPathCodes(lamda, count, path, 0, FactorNumberMapping.factorName2No(s).intValue());
                String fname = s;
                List<MiniBean> miniBeans = new ArrayList<>();
                for (String code : tmp) {
                    miniBeans.add(findFundNameByCode(code));
                }
                result.add(new CategoryFundBean(fname, miniBeans));
            }
            return result;
        }
        return Collections.EMPTY_LIST;
    }

    /**
     * 根据基金代码获得名字和代码
     *
     * @param code
     * @return
     */
    private MiniBean findFundNameByCode(String code) {
        code = fillCode(code);
        Fund fund = fundRepository.findByCode(code);
        return fund == null ? null : new MiniBean(code, fund.getName());
    }

    /**
     * @param fundCategoryBeanList 原来的
     * @param categoryFundBeanList 现在的
     * @return
     */
    private boolean compareCategories(List<FundCategoryBean> fundCategoryBeanList, List<CategoryFundBean> categoryFundBeanList) {
        for (FundCategoryBean fundCategoryBean : fundCategoryBeanList) {
            for (CategoryFundBean categoryFundBean : categoryFundBeanList) {
                if (fundCategoryBean.category.equals(categoryFundBean.name)) {
                    if (!compareCategory(fundCategoryBean, categoryFundBean)) {
                        return false;
                    }
                } else {
                    continue;
                }
            }
        }
        return true;
    }

    /**
     * 如果现在的仍包含原来的，则返回true；否则false
     *
     * @param fundCategoryBean 原来的
     * @param categoryFundBean 现在的
     * @return
     */
    private boolean compareCategory(FundCategoryBean fundCategoryBean, CategoryFundBean categoryFundBean) {
        if (!fundCategoryBean.category.equals(categoryFundBean.name)) {
            return false;
        }

        for (String code : fundCategoryBean.codes) {
            if (!categoryFundBeanContains(categoryFundBean, code)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 如果现在的包含原来的，返回true；否则false
     *
     * @param categoryFundBean 现在的
     * @param code             原来的中的一个
     * @return
     */
    private boolean categoryFundBeanContains(CategoryFundBean categoryFundBean, String code) {
        for (MiniBean miniBean : categoryFundBean.funds) {
            if (miniBean.code.equals(code)) {
                return true;
            }
        }
        return false;
    }
}
