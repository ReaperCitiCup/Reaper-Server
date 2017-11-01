package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundNetValue;

import java.util.Date;
import java.util.List;

public interface FundNetValueRepository extends JpaRepository<FundNetValue,Integer>{
    @Query(value = "select f from FundNetValue f where code=?1 order by date asc")
    public List<FundNetValue> findAllByCodeOrderByDateAsc(String code);

    @Query(value = "select f from FundNetValue f where code=?1 and date>?2 order by date asc")
    public List<FundNetValue> findAllByCodeAndDateAfterOrderByDateAsc(String code, Date date);

    @Query(value = "select f from FundNetValue f where code=?1 and (unitNetValue=null)=false order by date asc")
    public FundNetValue findFirstByCodeAndUnitNetValueNotNullOrderByDateDesc(String code);

    @Query(value = "select f from FundNetValue f where code=?1 order by date desc ")
    public List<FundNetValue> findAllByCodeOrderByDateDesc(String code);

    @Query(value = "select f from FundNetValue f where code=?1 and date=?2")
    public FundNetValue findByCodeAndDate(String code,Date date);

    @Query(value = "select f from FundNetValue f where code=?1 and date between ?1 and ?2")
    public List<FundNetValue> findAllByCodeAndDateBetween(String code, Date date1, Date date2);
}
