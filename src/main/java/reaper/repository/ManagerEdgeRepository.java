package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.ManagerEdge;
import reaper.model.ManagerRelation;

import java.util.List;

/**
 * Created by max on 2017/9/11.
 */
public interface ManagerEdgeRepository  extends JpaRepository<ManagerEdge,Integer> {
    public List<ManagerEdge> findAllByManagerIdA(String managerIdA);

    public List<ManagerEdge> findAllByManagerIdB(String managerIdB);
}
