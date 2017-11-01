package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundManager;

import java.util.List;

public interface FundManagerRepository extends JpaRepository<FundManager,Integer>{
    @Query(value = "select f from FundManager f where fundCode=?1")
    public List<FundManager> findByFundCode(String fundCode);

    @Query(value = "select f from FundManager f where managerId=?1")
    public List<FundManager> findByManagerId(String managerId);
}
