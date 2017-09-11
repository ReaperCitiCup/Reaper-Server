package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundNetValue;

import java.util.Date;
import java.util.List;

public interface FundNetValueRepository extends JpaRepository<FundNetValue,Integer>{
    public List<FundNetValue> findAllByCodeOrderByDateAsc(String code);

    public List<FundNetValue> findAllByCodeAndDateAfterOrderByDateAsc(String code, Date date);

    public FundNetValue findFirstByCodeOrderByDateDesc(String code);

    public List<FundNetValue> findAllByCodeOrderByDateDesc(String code);

}
