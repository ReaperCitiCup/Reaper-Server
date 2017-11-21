package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.NetworkRank;

public interface NetworkRankRepository extends JpaRepository<NetworkRank,String>{
}
