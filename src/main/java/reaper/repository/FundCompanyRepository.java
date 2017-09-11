package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.FundCompany;

import java.util.List;

public interface FundCompanyRepository extends JpaRepository<FundCompany,Integer>{
    public FundCompany findByFundId(String fundId);

    public List<FundCompany> findAllByCompanyId(String company);
}
