package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundScore;

public interface FundScoreRepository extends JpaRepository<FundScore,Integer>{
    public FundScore findByCode(String code);
}
