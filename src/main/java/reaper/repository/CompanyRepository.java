package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    public Company findByCompanyId(String companyId);
}
