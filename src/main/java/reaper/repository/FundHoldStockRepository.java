package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundHoldStock;

public interface FundHoldStockRepository extends JpaRepository<FundHoldStock,Integer>{
}
