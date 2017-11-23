package reaper.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reaper.bean.ValueDateBean;
import reaper.model.Combination;
import reaper.repository.CombinationRepository;
import reaper.util.FormatData;
import reaper.util.backtest_util.PyAnalysisResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static reaper.util.backtest_util.BackTestPyAnalysisGetter.getBasicFactors;

/**
 * 每天凌晨两点定时更新组合的简要信息
 *
 * @author keenan on 01/11/2017
 */
@SuppressWarnings("Duplicates")
@Component
public class CombinationBriefScheduler {
    @Autowired
    private CombinationRepository combinationRepository;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Scheduled(cron = "0 0 14 * * ?")
    public void updateCombinationBrief() throws Exception {
        List<Combination> combinations = combinationRepository.findAll();

        for (Combination combination : combinations) {
            String[] codes = combination.getFunds().split("\\|");
            String[] weights = combination.getWeights().split("\\|");
            List<Double> percentage = new ArrayList<>();
            for (String weight : weights) {
                percentage.add(Double.valueOf(weight) / 100.00);
            }
            PyAnalysisResult result = getBasicFactors(Arrays.asList(codes), percentage, "1900-05-05", simpleDateFormat.format(new Date()));

            /**
             * 更新年化收益率
             */
            try {
                double annualProfit = result.getNhsyl();
                combination.setAnnualProfit(FormatData.fixToTwoAndPercent(annualProfit));
            } catch (Exception e) {
                e.printStackTrace();
                combination.setAnnualProfit(0.0);
            }


            /**
             * 更新年化波动率
             */
            try {
                double volatility = result.getNhbdl();
                combination.setVolatility(FormatData.fixToTwoAndPercent(volatility));
            } catch (Exception e) {
                e.printStackTrace();
                combination.setVolatility(0.0);
            }

            /**
             * 更新最新收益率
             */
            try {
                List<ValueDateBean> returnRates = result.getDailyReturnRate();
                double newProfit = returnRates.get(returnRates.size() - 1).value;
                combination.setNewProfit(FormatData.fixToTwoAndPercent(newProfit));
            } catch (Exception e) {
                e.printStackTrace();
                combination.setNewProfit(0.0);
            }

            combinationRepository.save(combination);
        }
    }
}
