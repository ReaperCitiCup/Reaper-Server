package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.Manager;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager,Integer>{
    public Manager findByManagerId(String managerId);

    @Query(value="SELECT * FROM manager WHERE companyId=?1",
            nativeQuery = true)
    List<Manager> findCompanyManager(String company);

    @Query(value = "SELECT * FROM manager WHERE companyId<>?1 AND " +
            "bestReturns<=200 AND risk<=20;",
            nativeQuery = true)
    List<Manager> findOtherMangerByCompanyId(String company);

    @Query(value = "SELECT * FROM manager WHERE managerId<>?1 AND " +
            "bestReturns/100 <=200 AND risk<=20;",nativeQuery = true)
    List<Manager> findOtherManagerByManagerId(String manager);
}
