package reaper.util;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author keenan on 31/10/2017
 */
@Component
public class SchedulerUtil {
    @Scheduled(cron = "0 0 3 * * ?")
    public void sayHello() throws Exception {
        System.out.println("Hello " + LocalDateTime.now());
    }

}
