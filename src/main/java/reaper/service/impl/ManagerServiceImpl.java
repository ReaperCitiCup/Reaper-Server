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

    @Autowired
    FundManagerRepository fundManagerRepository;

    @Autowired
    ManagerEdgeRepository managerEdgeRepository;


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 根据经理id获得经理信息
     * @param id 经理id
     * @return 经理信息
     */
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

    /**
     * 根据经理id获得经理历史基金信息
     * @param id 经理id
     * @return 经理历史基金信息
     */
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

    /**
     * 根据经理id获得经理任期中的基金收益
     * @param managerId 经理id
     * @return 经理任期中的基金收益
     */
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

    /**
     * 根据经理id获得经理现任基金排名
     * @param managerId 经理id
     * @return 经理现任基金排名
     */
    //TODO
    @Override
    public List<RankBean> findFundRankByManagerId(String managerId) {
        List<RankBean> res=new ArrayList<>();
        List<FundHistory> fundHistories = fundHistoryRepository.findAllByManagerId(managerId);
        for(FundHistory fundHistory : fundHistories){

        }
        return null;
    }

    /**
     * 根据经理id获得经理现任基金收益率走势
     * @param managerId 经理id
     * @return 经理现任基金收益率走势
     */
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

    /**
     * 根据经理id获得经理现任基金排名走势
     * @param managerId 经理id
     * @return 经理现任基金排名走势
     */
    //TODO
    @Override
    public List<RankTrendBean> findFundRankTrendByManagerId(String managerId) {
        return null;
    }

    /**
     * 根据经理id获得经理历任基金表现
     * @param managerId 经理id
     * @return 经理历任基金表现
     */
    @Override
    public FundPerformanceBean findFundPerformanceByManagerId(String managerId) {
        List<PerformanceDataBean> funds = new ArrayList<>();
        List<PerformanceDataBean> others = new ArrayList<>();
        //经理所持基金
        try {
            funds = addFundPerformOfManager(funds, managerId);
        }catch (NullPointerException e){
            System.out.println(managerId);
        }
        //其他基金
        for(Fund fund:fundRepository.findAll()){
            PerformanceDataBean res = new PerformanceDataBean(fund);
            if(!funds.contains(res)){
                others.add(res);
            }
        }
        return new FundPerformanceBean(funds,others);
    }

    /**
     * 根据经理id获得经理综合表现
     * @param managerId 经理id
     * @return 经理综合表现
     * @apiNote 第一个为当前经理，剩下的为其他经理
     */
    @Override
    public ManagerPerformanceBean findManagerPerformanceByManagerId(String managerId) {
        List<PerformanceDataBean> managers = new ArrayList<>();
        List<PerformanceDataBean> others = new ArrayList<>();
        Manager manager = managerRepository.findByManagerId(managerId);
        if(manager!=null){
            managers.add(new PerformanceDataBean(manager));
        }
        for(Manager manager1 :managerRepository.findAll()){
            PerformanceDataBean res = new PerformanceDataBean(manager1);
            if(!managers.contains(res)){
                others.add(res);
            }
        }
        return new ManagerPerformanceBean(managers,others);
    }

    /**
     * 根据经理id获得经理综合能力
     * @param managerId 经理id
     * @return 经理综合能力
     */
    @Override
    public ManagerAbilityBean findManagerAbilityByManagerId(String managerId) {
        Manager manager=managerRepository.findByManagerId(managerId);
        if(manager!=null){
            ManagerRemark managerRemark=managerRemarkRepository.findByManagerId(Integer.parseInt(managerId));
            if(managerRemark!=null){
                return new ManagerAbilityBean(managerRemark);
            }

        }
        return null;
    }

    /**
     * 根据经理id获得经理社会关系网络图
     * @param managerId 经理id
     * @return 经理社会关系网络图
     */
    @Override
    public ManagerNetworkBean findSocialNetworkByManagerId(String managerId) {
        List<NodeDataBean> nodes = new ArrayList<>();
        List<ManagerLinkDataBean> links = new ArrayList<>();

        for(ManagerEdge managerEdge:getInterfacingCode(managerId, new ArrayList<>())){
            //记录两个点在node数组中的位置
            int indexA;
            int indexB;

            //先用id作为name，方便判断是否已经包含，最后一起转化为name
            NodeDataBean node = new NodeDataBean(managerEdge.getManagerIdA());
            int i = nodes.indexOf(node);
            if(i<0){
                nodes.add(node);
                indexA = nodes.size()-1;
            }else {
                indexA = i;
            }

            node = new NodeDataBean(managerEdge.getManagerIdB());
            i = nodes.indexOf(node);
            if(i<0){
                nodes.add(node);
                indexB = nodes.size()-1;
            }else {
                indexB = i;
            }
            links.add(new ManagerLinkDataBean(indexA,indexB,managerEdge.getDays(),managerEdge.getTimes()));
        }
        //把id转化成name
        return new ManagerNetworkBean(nodes,links);
    }

    private List<PerformanceDataBean> addFundPerformOfManager(List<PerformanceDataBean> list,String managerId){
        for(FundManager fundManager:fundManagerRepository.findByManagerId(managerId)){
            Fund fund = fundRepository.findByCode(fundManager.getFundCode());
            PerformanceDataBean res = new PerformanceDataBean(fund);
            if(fund!=null&&fund.getAnnualProfit()!=null&&!list.contains(res)) {
                list.add(res);
            }
        }
        return list;
    }

    /**
     * 递归查找所有关系
     * @param managerId 代码
     * @param managerEdges 保存结果的数组
     * @return
     */
    private List<ManagerEdge> getInterfacingCode(String managerId,List<ManagerEdge> managerEdges){
        //作为左端点
        for(ManagerEdge managerEdge:managerEdgeRepository.findAllByManagerIdA(managerId)){
            if(!managerEdges.contains(managerEdge)) {
                managerEdges.add(managerEdge);
                managerEdges = getInterfacingCode(managerEdge.getManagerIdA(),managerEdges);
            }
        }
        //作为右端点
        for(ManagerEdge managerEdge:managerEdgeRepository.findAllByManagerIdB(managerId)){
            if(!managerEdges.contains(managerEdge)) {
                managerEdges.add(managerEdge);
                managerEdges = getInterfacingCode(managerEdge.getManagerIdB(),managerEdges);
            }
        }
        return managerEdges;
    }
}
