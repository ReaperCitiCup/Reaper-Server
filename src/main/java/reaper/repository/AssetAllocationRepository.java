package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.AssetAllocation;

import java.util.List;

public interface AssetAllocationRepository extends JpaRepository<AssetAllocation, Integer>{
    public AssetAllocation findByCode(String code);

    //TODO 未测试
    @Query(value = "SELECT * FROM asset_allocation WHERE companyId=?1 AND " +
            "bank IS NOT NULL;",
            nativeQuery = true)
    List<AssetAllocation> findByCompany(String companyId);
}
