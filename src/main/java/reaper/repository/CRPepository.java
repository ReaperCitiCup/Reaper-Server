package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.CRP;
import reaper.model.FundCompany;

/**
 * Created by max on 2017/9/12.
 */
public interface CRPepository  extends JpaRepository<CRP,Integer> {
}
