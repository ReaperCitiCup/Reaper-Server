package reaper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundShortMessage;
import reaper.model.IndustryRate;

/**
 * Created by max on 2017/8/21.
 */
public interface IndustryRateRepository extends JpaRepository<IndustryRate,Integer>{

}
