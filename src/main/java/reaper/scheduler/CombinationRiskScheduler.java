package reaper.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 每天凌晨三点定时更新组合风险
 *
 * @author keenan on 01/11/2017
 */
@Component
public class CombinationRiskScheduler {
    @Scheduled(cron = "0 0 3 * * ?")
    public void updateRisk() throws Exception {
        //TODO 定时更新组合风险需要添加新的数据库表来实现
    }
}
