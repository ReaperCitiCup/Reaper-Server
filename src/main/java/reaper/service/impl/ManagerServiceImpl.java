package reaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reaper.bean.CompanyMiniBean;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerBean;
import reaper.model.Company;
import reaper.model.FundHistory;
import reaper.model.Manager;
import reaper.model.ManagerCompany;
import reaper.repository.*;
import reaper.service.ManagerService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feng on 2017/8/23.
 */

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ManagerCompanyRespository managerCompanyRespository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FundHistoryRepository fundHistoryRepository;

    @Autowired
    FundRepository fundRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ManagerBean findManagerById(String id) {
        List<Manager> managers=managerRepository.findByManagerId(id);
        if(managers!=null){
            Manager manager=managers.get(0);
            List<ManagerCompany> managerCompanys=managerCompanyRespository.findByManagerId(manager.getManagerId());
            if(managerCompanys!=null){
                List<Company> companys=companyRepository.findByCompanyId(managerCompanys.get(0).getCompanyId());
                if(companys!=null){
                    return  new ManagerBean(manager.getManagerId(), manager.getName(), manager.getGender(), sdf.format(manager.getAppointedDate()),
                            new CompanyMiniBean(companys.get(0).getCompanyId(), companys.get(0).getName()), manager.getTotalScope(), manager.getBestReturns(), manager.getIntroduction());
                }
            }
        }
        return null;
    }

    @Override
    public List<FundHistoryBean> findFundHistoryById(String id) {
        List<FundHistoryBean> res=new ArrayList<>();
        List<FundHistory> fundHistories=fundHistoryRepository.findAllByManagerId(id);
        if(fundHistories!=null){
            for(FundHistory fundHistory:fundHistories){
                res.add(new FundHistoryBean(fundHistory.getFundCode(),fundHistory.getFundName(),fundHistory.getFundType(),
                        fundRepository.findByFundCode(fundHistory.getFundCode()).get(0).getScope(), sdf.format(fundHistory.getStartDate()), sdf.format(fundHistory.getEndDate()),
                        (int)((fundHistory.getEndDate().getTime()-fundHistory.getStartDate().getTime())/(1000*3600*24)),
                        fundHistory.getPayback()));
            }
        }
        return res;
    }
}
