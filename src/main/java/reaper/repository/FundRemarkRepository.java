package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundRankByType;
import reaper.model.FundRemark;

import java.util.Date;
import java.util.List;

public interface FundRemarkRepository extends JpaRepository<FundRemark,Integer>{
    @Query(value = "select f from FundRemark f where code=?1 and startDate>?2")
    public List<FundRemark> findAllByCodeAndStartDateAfter(String code, Date startDate);



}