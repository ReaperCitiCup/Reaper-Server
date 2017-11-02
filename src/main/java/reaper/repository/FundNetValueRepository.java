package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundNetValue;

import java.util.Date;
import java.util.List;

public interface FundNetValueRepository extends JpaRepository<FundNetValue,Integer>{
    public List<FundNetValue> findAllByCodeOrderByDateAsc(String code);

    public List<FundNetValue> findAllByCodeAndDateAfterOrderByDateAsc(String code, Date date);

    public FundNetValue findFirstByCodeAndUnitNetValueNotNullOrderByDateDesc(String code);

    public List<FundNetValue> findAllByCodeOrderByDateDesc(String code);

    public FundNetValue findByCodeAndDate(String code,Date date);

    public List<FundNetValue> findAllByCodeAndDateBetween(String code, Date date1, Date date2);
}
