package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Manager;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager,Integer>{
    public List<Manager> findByManagerId(String managerId);
}
