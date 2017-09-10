package reaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reaper.bean.CompanyMiniBean;
import reaper.bean.FundHistoryBean;
import reaper.bean.ManagerAbilityBean;
import reaper.bean.ManagerBean;
import reaper.bean.*;
import reaper.model.*;
import reaper.repository.*;
import reaper.service.ManagerService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    ManagerRemarkRepository managerRemarkRepository;

    @Autowired
    FundNetValueRepository fundNetValueRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ManagerBean findManagerById(String id) {
        Manager manager=managerRepository.findByManagerId(id);
        if(manager!=null){
            ManagerCompany managerCompany=managerCompanyRespository.findByManagerId(manager.getManagerId());
            if(managerCompany!=null){
                Company company=companyRepository.findByCompanyId(managerCompany.getCompanyId());
                if(company!=null){
                    return  new ManagerBean(manager.getManagerId(), manager.getName(), sdf.format(manager.getAppointedDate()),
                            String.valueOf((int)((new Date().getTime()-manager.getAppointedDate().getTime())/(1000*3600*24))),
                            new CompanyMiniBean(company.getCompanyId(), company.getName()), manager.getTotalScope(), manager.getBestReturns(), manager.getIntroduction());
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
                Fund fund=fundRepository.findByCode(fundHistory.getFundCode());
                if(fund!=null){
                    List<String> type=new ArrayList<>();
                    type.add(fund.getType1());
                    type.add(fund.getType2());
                    //若fundHistory没有endDate, 则默认endDate是now
                    res.add(new FundHistoryBean(fundHistory.getFundCode(),fund.getName(),type,
                            fund.getScope(), sdf.format(fundHistory.getStartDate()),
                            sdf.format((fundHistory.getEndDate()==null)?new Date():fundHistory.getEndDate()),
                            (int)((((fundHistory.getEndDate()==null)?new Date():fundHistory.getEndDate()).getTime()-fundHistory.getStartDate().getTime())/(1000*3600*24)),
                            fundHistory.getPayback()));
                }
            }
        }
        return res;
    }

    //TODO 这里仍没有对不存在的managerId的处理(应该是加错位置了，加到performance那里了) + 已修改
    @Override
    public ManagerAbilityBean findManagerAbilityByManagerId(String managerId) {
<<<<<<< HEAD
        Manager manager=managerRepository.findByManagerId(managerId);
        if(manager!=null){
            ManagerAbility managerAbility=managerAbilityRepository.findByManagerId(managerId);
            if(managerAbility!=null){
                return new ManagerAbilityBean(managerAbility);
            }
=======
        ManagerRemark managerRemark = managerRemarkRepository.findByManagerId(Integer.valueOf(managerId));
        if(managerRemark!=null){
            return new ManagerAbilityBean(managerRemark);
>>>>>>> 89f891c2d3a7ff117d57c94c21434c3b546f12b2
        }
        return null;
    }

    @Override
    public List<ReturnBean> findFundReturnsByManagerId(String managerId) {
        List<ReturnBean> res=new ArrayList<>();
        List<FundHistory> fundHistories=fundHistoryRepository.findAllByManagerId(managerId);
        if(fundHistories!=null){
            for(FundHistory fundHistory : fundHistories){
                res.add(new ReturnBean(fundHistory.getFundCode(), fundHistory.getFundName(), fundHistory.getPayback()));
            }
        }
        return res;
    }

    //TODO
    @Override
    public List<RankBean> findFundRankByManagerId(String managerId) {
        List<RankBean> res=new ArrayList<>();
        List<FundHistory> fundHistories = fundHistoryRepository.findAllByManagerId(managerId);
        for(FundHistory fundHistory : fundHistories){

        }
        return null;
    }

    @Override
    public List<RateTrendBean> findFundRateTrendByManagerId(String managerId) {
        List<RateTrendBean> res=new ArrayList<>();
        List<FundHistory> fundHistories=fundHistoryRepository.findAllByManagerId(managerId);
        if(fundHistories!=null){
            for(FundHistory fundHistory : fundHistories){
                List<RateTrendDataBean> data=new ArrayList<>();
                List<FundNetValue> fundNetValues=fundNetValueRepository.findAllByCodeOrderByDateAsc(fundHistory.getFundCode());
                if(fundNetValues!=null){
                    for (FundNetValue fundNetValue : fundNetValues){
                        data.add(new RateTrendDataBean(sdf.format(fundNetValue.getDate()), fundNetValue.getUnitNetValue()));
                    }
                }
                res.add(new RateTrendBean(fundHistory.getFundCode(),fundHistory.getFundName(),data));
            }
        }
        return res;
    }

    //TODO
    @Override
    public List<RankTrendBean> findFundRankTrendByManagerId(String managerId) {
        return null;
    }

    @Override
    public FundPerformanceBean findFundPerformanceByManagerId(String managerId) {
        return null;
    }

    @Override
<<<<<<< HEAD
    public ManagerPerformanceBean findManagerPerformanceByManagerId(String managerId) {
        return null;
    }

    @Override
    public NetworkBean findSocialNetworkByManagerId(String managerId) {
        return null;
=======
    public List<ManagerPerformanceBean> findManagerPerformanceByManagerId(String managerId) {
        List<ManagerPerformanceBean> res=new ArrayList<>();
        Manager manager=managerRepository.findByManagerId(managerId);
        if(manager!=null){
            ManagerRemark managerRemark = managerRemarkRepository.findByManagerId(Integer.valueOf(managerId));
            if(managerRemark!=null){
                res.add(new ManagerPerformanceBean(managerId,manager.getName(),Double.valueOf(managerRemark.getYieldAbility()),Double.valueOf(managerRemark.getWindControlAbility())));
            }
        }
        return res;
>>>>>>> 89f891c2d3a7ff117d57c94c21434c3b546f12b2
    }

}
