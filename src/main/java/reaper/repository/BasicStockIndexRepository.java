package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.BasicStockIndex;

import reaper.model.BrisonResult;

import java.util.Date;
import java.util.List;

/**
 * Created by max on 2017/9/11.
 */
public interface BasicStockIndexRepository extends JpaRepository<BasicStockIndex,Integer> {

    public List<BasicStockIndex> findAllByStockNameAndDateBetweenOrderByDateAsc(String stockName, Date date1, Date date2);
}
