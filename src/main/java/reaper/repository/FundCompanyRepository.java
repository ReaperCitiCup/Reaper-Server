package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.FundCompany;

import java.util.List;

public interface FundCompanyRepository extends JpaRepository<FundCompany,Integer>{
    @Query(value = "select f from FundCompany f where fundId=?1")
    public FundCompany findByFundId(String fundId);

    @Query(value = "select f from FundCompany f where companyId=?1")
    public List<FundCompany> findAllByCompanyId(String company);
}
