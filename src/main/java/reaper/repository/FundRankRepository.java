package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundRank;

public interface FundRankRepository extends JpaRepository<FundRank,String>{
}
