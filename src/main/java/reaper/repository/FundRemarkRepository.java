package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundRankByType;
import reaper.model.FundRemark;

import java.util.List;

public interface FundRemarkRepository extends JpaRepository<FundRemark,Integer>{
    public List<FundRankByType> findAllByCode(String code);



}