package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundManager;

public interface FundManagerRepository extends JpaRepository<FundManager,Integer>{
}
