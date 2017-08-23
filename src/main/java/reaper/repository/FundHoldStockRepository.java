package reaper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundHoldStock;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface FundHoldStockRepository extends JpaRepository<FundHoldStock,Integer>{
    public Page<FundHoldStock> findAllByStockCodeLike(String code, Pageable pageable);
}
