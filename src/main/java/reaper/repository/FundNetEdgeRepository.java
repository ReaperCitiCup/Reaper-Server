package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundNetEdge;
import reaper.model.FundNetValue;

import java.util.List;

/**
 * Created by max on 2017/9/11.
 */
public  interface FundNetEdgeRepository extends JpaRepository<FundNetEdge,Integer> {
    @Query(value = "select f from FundNetEdge f where codeIdA=?1")
    public List<FundNetEdge> findAllByCodeIdA(String code);

    @Query(value = "select f from FundNetEdge f where codeIdB=?1")
    public List<FundNetEdge> findAllByCodeIdB(String code);
}
