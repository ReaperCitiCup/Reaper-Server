package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundCompany;

public interface FundCompanyRepository extends JpaRepository<FundCompany,Integer>{
}
