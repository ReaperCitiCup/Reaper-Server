package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundHoldBond;

public interface FundHoldBondRepository extends JpaRepository<FundHoldBond,Integer>{
    
}
