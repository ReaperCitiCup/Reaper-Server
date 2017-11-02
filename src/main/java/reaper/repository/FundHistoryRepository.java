package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundHistory;

import java.util.Date;
import java.util.List;

public interface FundHistoryRepository extends JpaRepository<FundHistory,Integer>{
    public List<FundHistory> findAllByFundCodeOrderByStartDateAsc(String fundCode);

    public List<FundHistory> findAllByManagerId(String managerId);

    public List<FundHistory> findAllByFundCode(String fundCode);

    public List<FundHistory> findAllByManagerIdAndStartDateBefore(String managerId, Date startDate);

    public List<FundHistory> findAllByFundCodeAndAndEndDateIsNull(String fundCode);

    public List<FundHistory> findAllByManagerIdAndAndEndDateIsNull(String managerId);
}
