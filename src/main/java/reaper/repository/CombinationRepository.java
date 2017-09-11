package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Combination;

import java.util.List;

/**
 * @author keenan on 10/09/2017
 */
public interface CombinationRepository extends JpaRepository<Combination, Integer> {
//    public Combination findCombinationById(Integer id);

    public List<Combination> findCombinationsByUserid(Integer userid);
}
