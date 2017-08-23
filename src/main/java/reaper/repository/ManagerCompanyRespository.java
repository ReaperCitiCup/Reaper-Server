package reaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import reaper.model.ManagerCompany;

import java.util.List;

public interface ManagerCompanyRespository extends JpaRepository<ManagerCompany,Integer>{
    public List<ManagerCompany> findByManagerId(String managerId);
}
