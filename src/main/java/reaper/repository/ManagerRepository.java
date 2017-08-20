package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager,Integer>{
}
