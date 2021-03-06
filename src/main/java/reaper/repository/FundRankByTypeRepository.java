package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundRankByType;

import java.util.List;

public interface FundRankByTypeRepository extends JpaRepository<FundRankByType,Integer>{
    public List<FundRankByType> findAllByCode(String code);



}