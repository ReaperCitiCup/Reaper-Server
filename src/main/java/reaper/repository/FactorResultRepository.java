package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FactorResult;

public interface FactorResultRepository extends JpaRepository<FactorResult,Integer>{
    public FactorResult findByCodeAndFactorType(String code,Character factorType);
}
