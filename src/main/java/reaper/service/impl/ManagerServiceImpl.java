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
    ManagerAbilityRepository managerAbilityRepository;

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

    //TODO 这里仍没有对不存在的managerId的处理
    @Override
    public ManagerAbilityBean findManagerAbilityByManagerId(String managerId) {
        return new ManagerAbilityBean(managerAbilityRepository.findByManagerId(managerId));
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
    public List<FundPerformanceBean> findFundPerformanceByManagerId(String managerId) {
        List<FundPerformanceBean> res=new ArrayList<>();
        List<FundHistory> fundHistories=fundHistoryRepository.findAllByManagerId(managerId);
        if(fundHistories!=null){
            for(FundHistory fundHistory:fundHistories){
                Fund fund=fundRepository.findByCode(fundHistory.getFundCode());
                if(fund!=null){
                    res.add(new FundPerformanceBean(fund.getCode(),fund.getName(),fund.getAnnualProfit(),fund.getVolatility()));
                }
            }
            return res;
        }
        return null;
    }

    @Override
    public List<ManagerPerformanceBean> findManagerPerformanceByManagerId(String managerId) {
        Manager manager=managerRepository.findByManagerId(managerId);
        if(manager!=null){
            List<ManagerPerformanceBean> res=new ArrayList<>();
            ManagerAbility managerAbility =managerAbilityRepository.findByManagerId(managerId);
            if(managerAbility!=null){
                res.add(new ManagerPerformanceBean(managerAbility.getManagerId(),manager.getName(),managerAbility.getReturns(),managerAbility.getAntirisk()));
            }
            return res;
        }
        return null;
    }

}
