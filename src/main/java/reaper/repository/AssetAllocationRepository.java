package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.AssetAllocation;

public interface AssetAllocationRepository extends JpaRepository<AssetAllocation, Integer>{
    public AssetAllocation findByCode(String code);
}
