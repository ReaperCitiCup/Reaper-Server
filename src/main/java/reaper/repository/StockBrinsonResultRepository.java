package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.StockBrinsonResult;
import reaper.model.TotalPortion;

/**
 * Created by max on 2017/9/12.
 */
public interface StockBrinsonResultRepository  extends JpaRepository<StockBrinsonResult,String> {
}
