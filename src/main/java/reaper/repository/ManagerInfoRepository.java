package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.ManagerInfo;

public interface ManagerInfoRepository extends JpaRepository<ManagerInfo,Integer>{
    public ManagerInfo findByCode(String code);
}
