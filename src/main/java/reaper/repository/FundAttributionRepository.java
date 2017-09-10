package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundAttribution;

public interface FundAttributionRepository extends JpaRepository<FundAttribution,Integer>{
}
