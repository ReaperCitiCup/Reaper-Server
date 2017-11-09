package reaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reaper.bean.FieldValueBean;
import reaper.bean.FundPerformanceBean;
import reaper.bean.ManagerPerformanceBean;
import reaper.bean.PerformanceDataBean;
import reaper.model.*;
import reaper.repository.*;
import reaper.service.CompanyService;
import reaper.util.Crawler;
import reaper.util.FormatData;
import reaper.util.ToFieldBean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Feng on 2017/9/2.
 */

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    FundRepository fundRepository;

    @Autowired
    FundCompanyRepository fundCompanyRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    ManagerCompanyRespository managerCompanyRespository;

    @Autowired
    AssetAllocationRepository assetAllocationRepository;

    @Autowired
    FactorResultRepository factorResultRepository;

    /**
     * 根据公司id获得公司旗下基金表现
     *
     * @param companyId 公司id
     * @return 公司旗下基金表现
     */
    @Override
    public FundPerformanceBean findFundPerformanceByCompanyId(String companyId) {
        //该公司所有的fund
        List<Fund> companyFund=fundRepository.findCompanyFund(companyId);
        List<PerformanceDataBean> funds = companyFund.parallelStream().map(PerformanceDataBean::new).collect(Collectors.toList());

        List<Fund> otherFund=fundRepository.findOtherFund(companyId);
        List<PerformanceDataBean> others = otherFund.parallelStream().map(PerformanceDataBean::new).collect(Collectors.toList());

//        for (FundCompany fundCompany : fundCompanies) {
//            try {
//                //所有fund的信息
//                Fund fund = fundRepository.findByCode(fundCompany.getFundId());
//                if (fund.getAnnualProfit() != null && fund.getVolatility() != null) {
//                    funds.add(new PerformanceDataBean(fund));
//                }
//            } catch (NullPointerException e) {
//                System.out.println(fundCompany.getFundId());
//                //若不存在，则动态爬取
//                Fund fund = new Crawler().crawlFundDetail(fundCompany.getFundId(), fundRepository);
//                if (fund.getAnnualProfit() != null && fund.getVolatility() != null) {
//                    funds.add(new PerformanceDataBean(fund));
//                }
//            }
//        }
//
//        for (Fund fund : fundRepository.findAll()) {
//            if (fund.getAnnualProfit() != null && fund.getVolatility() != null) {
//                PerformanceDataBean data = new PerformanceDataBean(fund);
//
//                //判断是否是公司的
//
//                if (!funds.contains(data) && data.risk <= 50 && data.rate >= -100 && data.rate <= 100) {
//                    others.add(data);
//                }
//            }
//        }
        return new FundPerformanceBean(funds, others);
    }

    /**
     * 根据公司id获得公司旗下经理表现
     *
     * @param companyId 公司id
     * @return 公司旗下经理表现
     */
    @Override
    public ManagerPerformanceBean findManagerPerformanceByCompanyId(String companyId) {
        //公司经理
        //TODO 想不起原来为什么returnRate要除以100了，先这样改完，有问题再改吧～
        List<Manager> companyManager=managerRepository.findCompanyManager(companyId);
        List<PerformanceDataBean> managers = companyManager.parallelStream().map(PerformanceDataBean::new).collect(Collectors.toList());
        List<Manager> otherManager=managerRepository.findOtherMangerByCompanyId(companyId);
        List<PerformanceDataBean> others = otherManager.parallelStream().map(PerformanceDataBean::new).collect(Collectors.toList());

//        for (ManagerCompany managerCompany : managerCompanies) {
//            try {
//                Manager manager = managerRepository.findByManagerId(managerCompany.getManagerId());
//                //
//                if (manager.getReturnRate() != null && manager.getRisk() != null) {
//                    managers.add(new PerformanceDataBean(manager.getManagerId(), manager.getName(),
//                            manager.getReturnRate() / 100, manager.getRisk()));
//                }
//            } catch (NullPointerException e) {
//                System.out.println(managerCompany.getManagerId());
//                //TODO 扒经理代码不知怎么不见了，假如有空指针再去找找
//            }
//        }
//
//        for (Manager manager : managerRepository.findAll()) {
//            if (manager.getRisk() != null && manager.getReturnRate() != null) {
//                PerformanceDataBean data = new PerformanceDataBean(manager);
//                data.rate /= 100;
//                if (!managers.contains(data) && data.rate <= 200 && data.risk <= 20) {
//                    others.add(data);
//                }
//            }
//        }

        return new ManagerPerformanceBean(managers, others);
    }

    /**
     * 根据公司id获得公司资产配置行业占比
     *
     * @param companyId 公司id
     * @return 公司资产配置行业占比
     */
    @Override
    public List<FieldValueBean> findAssetAllocationByCompanyId(String companyId) {
        //计数，非null数值
        int count = 0;
        double stock = 0;
        double bond = 0;
        double bank = 0;

        List<AssetAllocation> assetAllocations=assetAllocationRepository.findByCompany(companyId);
        stock=assetAllocations.parallelStream().mapToDouble(AssetAllocation::getStock).sum();
        bond=assetAllocations.parallelStream().mapToDouble(AssetAllocation::getBond).sum();
        //TODO 卧槽，bank那一列怎么全是零
        bank=assetAllocations.parallelStream().mapToDouble(AssetAllocation::getBank).sum();
        count=assetAllocations.size();

//        for (FundCompany fundCompany : fundCompanies) {
//            AssetAllocation assetAllocation = assetAllocationRepository.findByCode(fundCompany.getFundId());
//            if (assetAllocation == null || assetAllocation.getBank() == null) {
//                continue;
//            }
//            stock += assetAllocation.stock;
//            bond += assetAllocation.bond;
//            bank += assetAllocation.bank;
//            count++;
//        }
        List<FieldValueBean> res = new ArrayList<>();
        if(count!=0) {
            res.add(new FieldValueBean("股票", FormatData.fixToTwo(stock / count)));
            res.add(new FieldValueBean("银行", FormatData.fixToTwo(bank / count)));
            res.add(new FieldValueBean("债券", FormatData.fixToTwo(bond / count)));
            res.add(new FieldValueBean("其他", FormatData.fixToTwo(100 - stock / count - bank / count - bond / count)));
        }
        else{
            res.add(new FieldValueBean("股票", FormatData.fixToTwo(0.0)));
            res.add(new FieldValueBean("银行", FormatData.fixToTwo(0.0)));
            res.add(new FieldValueBean("债券", FormatData.fixToTwo(0.0)));
            res.add(new FieldValueBean("其他", FormatData.fixToTwo(0.0)));
        }

        return res;
    }

    /**
     * 根据公司id获得公司风格归因-主动收益
     *
     * @param companyId 公司id
     * @return 公司风格归因-主动收益
     */
    @Override
    public List<FieldValueBean> findStyleAttributionProfitByCompanyId(String companyId) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(companyId, 'N');
        return ToFieldBean.factorResultToStyleAttribution(factorResult);
    }

    /**
     * 根据公司id获得公司风格归因-主动风险
     *
     * @param companyId 公司id
     * @return 公司风格归因-主动风险
     */
    @Override
    public List<FieldValueBean> findStyleAttributionRiskByCompanyId(String companyId) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(companyId, 'R');
        return ToFieldBean.factorResultToStyleAttribution(factorResult);
    }

    /**
     * 根据公司id获得公司行业归因-主动收益
     *
     * @param companyId 公司id
     * @return 公司行业归因-主动收益
     */
    @Override
    public List<FieldValueBean> findIndustryAttributionProfitByCompanyId(String companyId) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(companyId, 'N');
        return ToFieldBean.factorResultToIndustryAttribution(factorResult);
    }

    /**
     * 根据公司id获得公司行业归因-主动风险
     *
     * @param companyId 公司id
     * @return 公司行业归因-主动风险
     */
    @Override
    public List<FieldValueBean> findIndustryAttributionRiskByCompanyId(String companyId) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(companyId, 'R');
        return ToFieldBean.factorResultToIndustryAttribution(factorResult);
    }
}
