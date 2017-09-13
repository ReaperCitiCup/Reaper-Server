package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundRankByType;

import java.util.List;

public interface FundRemarkRepository extends JpaRepository<FundRankByType,Integer>{
    public List<FundRankByType> findAllByCode(String code);



}