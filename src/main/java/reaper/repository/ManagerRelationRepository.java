package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Manager;
import reaper.model.ManagerRelation;

import java.util.List;

public interface ManagerRelationRepository extends JpaRepository<ManagerRelation,Integer>{

}
