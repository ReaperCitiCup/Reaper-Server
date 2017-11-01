package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.Combination;

import java.util.List;

/**
 * @author keenan on 10/09/2017
 */
public interface CombinationRepository extends JpaRepository<Combination, Integer> {
//    public Combination findCombinationById(Integer id);

    @Query(value = "select c from Combination where userid=?1")
    public List<Combination> findCombinationsByUserid(Integer userid);
}
