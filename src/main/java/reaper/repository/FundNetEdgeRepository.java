package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundNetEdge;
import reaper.model.FundNetValue;

import java.util.List;

/**
 * Created by max on 2017/9/11.
 */
public  interface FundNetEdgeRepository extends JpaRepository<FundNetEdge,Integer> {
    public List<FundNetEdge> findAllByCodeIdA(String code);

    public List<FundNetEdge> findAllByCodeIdB(String code);
}
