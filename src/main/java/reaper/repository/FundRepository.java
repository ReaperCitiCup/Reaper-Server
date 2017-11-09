package reaper.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.Fund;

import java.util.List;


public interface FundRepository extends JpaRepository<Fund, Integer> {
    public Page<Fund> findAllByNameLike(String keyword, Pageable pageable);

    public Page<Fund> findAllByCodeContaining(String keyword, Pageable pageable);

    public Fund findByCode(String code);

    @Query(value="SELECT * FROM fund WHERE companyId<>?1 AND " +
            "annualProfit BETWEEN -100 AND 100 AND volatility <=50;",
            nativeQuery = true)
    public List<Fund> findOtherFund(String code);

    @Query(value = "SELECT * FROM fund WHERE companyId=?1 AND " +
            "annualProfit IS NOT NULL AND volatility IS NOT NULL;",
            nativeQuery = true)
    public List<Fund> findCompanyFund(String companyId);

    //TODO 未测试
    @Query(value = "SELECT * FROM fund WHERE annualProfit IS NOT NULL;",nativeQuery = true)
    List<Fund> findAllFundOfManagerService();
}
