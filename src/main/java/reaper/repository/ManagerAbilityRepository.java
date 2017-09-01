package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.ManagerAbility;

public interface ManagerAbilityRepository extends JpaRepository<ManagerAbility,Integer>{
    public ManagerAbility findByManagerId(String managerId);
}
