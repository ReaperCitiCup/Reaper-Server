package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.BasicStockIndex;
import reaper.model.BrisonResult;

/**
 * Created by max on 2017/9/11.
 */
public interface BasicStockIndexRepository extends JpaRepository<BasicStockIndex,Integer> {
}
