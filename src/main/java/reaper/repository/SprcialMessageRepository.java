package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundCompany;
import reaper.model.SpecialMessage;

/**
 * Created by max on 2017/8/22.
 */
public interface SprcialMessageRepository extends JpaRepository<SpecialMessage,Integer> {
}
