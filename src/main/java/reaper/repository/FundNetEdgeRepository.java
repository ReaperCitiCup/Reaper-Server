package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundNetEdge;
import reaper.model.FundNetValue;

/**
 * Created by max on 2017/9/11.
 */
public  interface FundNetEdgeRepository extends JpaRepository<FundNetEdge,Integer> {
}
