package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import reaper.model.Manager;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager,Integer>{
    public Manager findByManagerId(String managerId);

    //TODO 未测试
    @Query(value="SELECT * FROM manager WHERE companyId=?1 AND " +
            "returnRate IS NOT NULL risk IS NOT NULL;",
            nativeQuery = true)
    List<Manager> findCompanyManager(String company);

    //TODO 未测试,与NULL比较均为假
    @Query(value = "SELECT * FROM manager WHERE companyId=manage<>?1 AND " +
            "bestReturns<=200 AND risk<=20;",
            nativeQuery = true)
    List<Manager> findOtherMangerByCompanyId(String company);

    //TODO 未测试
    @Query(value = "SELECT * FROM manager WHERE managerId<>?1 AND " +
            "bestReturns<=200 AND risk<=20;",nativeQuery = true)
    List<Manager> findOtherManagerByManagerId(String manager);
}
