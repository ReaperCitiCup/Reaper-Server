package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundManager;

import java.util.List;

public interface FundManagerRepository extends JpaRepository<FundManager,Integer>{
    public List<FundManager> findByFundCode(String fundCode);

    public List<FundManager> findByManagerId(String managerId);
}
