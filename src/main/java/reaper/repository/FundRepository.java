package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Fund;

public interface FundRepository extends JpaRepository<Fund, Integer> {
}
