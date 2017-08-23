package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundHistory;

import java.util.List;

public interface FundHistoryRepository extends JpaRepository<FundHistory,Integer>{
    public List<FundHistory> findAllByFundCodeOrderByStartDateAsc(String fundCode);
}
