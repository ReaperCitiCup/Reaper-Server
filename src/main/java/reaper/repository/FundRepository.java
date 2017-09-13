package reaper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Fund;

import java.util.List;


public interface FundRepository extends JpaRepository<Fund, Integer> {
    public Page<Fund> findAllByNameLike(String keyword, Pageable pageable);

    public Page<Fund> findAllByCodeContaining(String keyword, Pageable pageable);

    public Fund findByCode(String code);
}
