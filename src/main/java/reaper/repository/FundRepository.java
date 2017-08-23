package reaper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Fund;


public interface FundRepository extends JpaRepository<Fund, Integer> {
    public Page<Fund> findAllByNameLike(String keyword, Pageable pageable);
}
