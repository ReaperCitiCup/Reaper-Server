package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.CPR;

/**
 * Created by max on 2017/9/12.
 */
public interface CPRRepository extends JpaRepository<CPR,Integer> {
    public CPR findByFundId(String fundId);
}
