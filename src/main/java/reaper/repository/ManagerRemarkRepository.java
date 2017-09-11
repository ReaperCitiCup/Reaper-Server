package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.ManagerRemark;

public interface ManagerRemarkRepository extends JpaRepository<ManagerRemark, Integer>{
    public ManagerRemark findByManagerId(Integer managerId);
}
