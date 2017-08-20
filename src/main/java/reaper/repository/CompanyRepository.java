package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
