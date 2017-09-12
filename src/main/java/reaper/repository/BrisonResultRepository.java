package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.BrisonResult;

public interface BrisonResultRepository extends JpaRepository<BrisonResult,Integer>{
    public BrisonResult findByCode(String code);
}
