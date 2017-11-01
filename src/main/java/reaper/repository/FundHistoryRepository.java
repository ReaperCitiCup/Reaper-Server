package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundHistory;

import java.util.Date;
import java.util.List;

public interface FundHistoryRepository extends JpaRepository<FundHistory,Integer>{
    @Query(value = "select f from FundHistory f where fundCode=?1 order by startDate asc")
    public List<FundHistory> findAllByFundCodeOrderByStartDateAsc(String fundCode);

    @Query(value = "select f from FundHistory f where managerId=?1")
    public List<FundHistory> findAllByManagerId(String managerId);

    @Query(value = "select f from FundHistory f where fundCode=?1")
    public List<FundHistory> findAllByFundCode(String fundCode);

    @Query(value = "select f from FundHistory f where managerId=?1 and startDate<?2")
    public List<FundHistory> findAllByManagerIdAndStartDateBefore(String managerId, Date startDate);

    @Query(value = "select f from FundHistory f where fundcode=?1 and endDate=null ")
    public List<FundHistory> findAllByFundCodeAndAndEndDateIsNull(String fundCode);

    @Query(value = "select f from FundHistory f where managerId=?1 and endDate=null")
    public List<FundHistory> findAllByManagerIdAndAndEndDateIsNull(String managerId);
}
