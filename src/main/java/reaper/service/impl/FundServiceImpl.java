package reaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reaper.bean.*;
import reaper.model.*;
import reaper.repository.*;
import reaper.service.FundService;
import reaper.util.DaysBetween;
import reaper.util.FundModelToBean;
import reaper.util.PythonUser;
import reaper.util.ToFieldBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static reaper.util.FormatData.*;

/**
 * Created by Sorumi on 17/8/21.
 */

@Service
public class FundServiceImpl implements FundService {
    @Autowired
    FundRepository fundRepository;

    @Autowired
    FundShortMessageRepository fundShortMessageRepository;

    @Autowired
    FundNetValueRepository fundNetValueRepository;

    @Autowired
    FundHistoryRepository fundHistoryRepository;

    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FundCompanyRepository fundCompanyRepository;

    @Autowired
    AssetAllocationRepository assetAllocationRepository;

    @Autowired
    FactorResultRepository factorResultRepository;

    @Autowired
    BrisonResultRepository brisonResultRepository;

    @Autowired
    StockBrinsonResultRepository stockBrinsonResultRepository;

    @Autowired
    CPRRepository cprRepository;

    @Autowired
    FundNetEdgeRepository fundNetEdgeRepository;

    @Autowired
    FundRemarkRepository fundRemarkRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public reaper.util.Page<FundMiniBean> findFundByKeyword(String keyword, String order, int size, int page) {
        reaper.util.Page<FundMiniBean> res = new reaper.util.Page<>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);

        org.springframework.data.domain.Page<Fund> fundPage = null;
        //判断传入的是否是代码
        if(keyword.matches("^\\d+$")) {
            fundPage = fundRepository.findAllByCodeContaining(keyword,new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, order == null ? "code" : order)));
        }else {
            //默认以升序进行排列
            fundPage = fundRepository.findAllByNameLike("%" + keyword + "%", new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, order == null ? "code" : order)));
        }
        List<FundMiniBean> miniBeans = new ArrayList<>();
        for (Fund fund : fundPage.getContent()) {
            //根据基金代码找到经理代码
            List<FundHistory> fundManagers = fundHistoryRepository.findAllByFundCodeAndAndEndDateIsNull(fund.getCode());
            //根据经理代码找到经理名
            List<MiniBean> managerList = new ArrayList<>();
            for(FundHistory fundManager:fundManagers){
                managerList.add(new MiniBean(fundManager.getManagerId(),fundManager.getManagerName()));
            }
            miniBeans.add(new FundMiniBean(fund.getCode(), fund.getName(), fund.getAnnualProfit(), fund.getVolatility(), managerList));
        }
        res.setResult(miniBeans);
        res.setTotalCount((int) fundPage.getTotalElements());
        return res;
    }

    /**
     * 判断code是否存在
     *
     * @param code 代码
     * @return
     */
    @Override
    public boolean checkCodeExist(String code) {
        return !(fundRepository.findByCode(code)==null);
    }

    @Override
    public MiniBean findFundNameByCode(String code) {
        code = fillCode(code);
        Fund fund = fundRepository.findByCode(code);
        return fund==null?null:new MiniBean(code,fund.getName());
    }

    @Override
    public FundBean findFundByCode(String code) {
        code = fillCode(code);

        Fund fund = fundRepository.findByCode(code);
        if(fund==null){
            return null;
        }
        FundModelToBean fundModelToBean = new FundModelToBean();

        FundNetValue fundNetValue = fundNetValueRepository.findFirstByCodeAndUnitNetValueNotNullOrderByDateDesc(code);
        RateBean rateBean = getFundRate(code);

        List<IdNameBean> managers = new ArrayList<>();
        for(FundHistory fundManager:fundHistoryRepository.findAllByFundCodeAndAndEndDateIsNull(code)){
            managers.add(new IdNameBean(fundManager.getManagerId(), fundManager.getManagerName()));
        }
        IdNameBean company = null;
        if(fund.getCompanyId()!=null) {
            company = new IdNameBean(fund.getCompanyId(), companyRepository.findByCompanyId(fund.getCompanyId()).getName());
        }
        return fundModelToBean.modelToBean(fund, fundNetValue, rateBean, managers, company);
    }

    @Override
    public List<ValueDateBean> findUnitNetValueTrendByCode(String code) {
        List<ValueDateBean> res = new ArrayList<>();

        for (FundNetValue fundNetValue : fundNetValueRepository.findAllByCodeOrderByDateAsc(fillCode(code))) {
            res.add(new ValueDateBean(sdf.format(fundNetValue.getDate()), fixToTwo(fundNetValue.getUnitNetValue())));
        }
        return res;
    }

    @Override
    public List<ValueDateBean> findCumulativeNetValueTrendByCode(String code) {
        List<ValueDateBean> res = new ArrayList<>();

        for (FundNetValue fundNetValue : fundNetValueRepository.findAllByCodeOrderByDateAsc(fillCode(code))) {
            res.add(new ValueDateBean(sdf.format(fundNetValue.getDate()), fixToTwo(fundNetValue.getCumulativeNetValue())));
        }
        return res;
    }

    @Override
    public List<ValueDateBean> findCumulativeRateTrendByCode(String code, String month) {
        code = fillCode(code);
        List<ValueDateBean> res = new ArrayList<>();

        //累加结果
        double cumulativeValue = 0;

        List<FundNetValue> fundNetValues;

        if (month.equals("all")) {
            fundNetValues = fundNetValueRepository.findAllByCodeOrderByDateAsc(code);
        } else {
            //获得n月前的日期
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            //若不是数字，则返回空串
            try {
                calendar.add(Calendar.MONTH, -Integer.valueOf(month));
            }catch (NumberFormatException e){
                return res;
            }

            fundNetValues = fundNetValueRepository.findAllByCodeAndDateAfterOrderByDateAsc(code, calendar.getTime());
        }

        for (FundNetValue fundNetValue : fundNetValues) {
            if(fundNetValue.getDailyRate()!=null) {
                cumulativeValue += fundNetValue.getDailyRate();
            }
            res.add(new ValueDateBean(sdf.format(fundNetValue.getDate()), fixToTwo(cumulativeValue)));
        }

        return res;
    }

    @Override
    public List<HistoryManagerBean> findHistoryManagersByCode(String code) {
        List<HistoryManagerBean> res = new ArrayList<>();

        for (FundHistory fundHistory : fundHistoryRepository.findAllByFundCodeOrderByStartDateAsc(fillCode(code))) {
            //计算相差的天数
            int difDays = DaysBetween.daysOfTwo(fundHistory.getStartDate(),fundHistory.getEndDate());
            res.add(new HistoryManagerBean(fundHistory.getManagerId(), fundHistory.getManagerName(), sdf.format(fundHistory.getStartDate()), sdf.format(fundHistory.getEndDate()), difDays, fundHistory.getPayback()));
        }
        return res;
    }

    @Override
    public CurrentAssetBean findCurrentAssetByCode(String code) {
        AssetAllocation assetAllocation = assetAllocationRepository.findByCode(fillCode(code));
        return assetAllocation==null?null:new CurrentAssetBean(Double.valueOf(fixToTwo(assetAllocation.bond)),Double.valueOf(fixToTwo(assetAllocation.stock)),Double.valueOf(fixToTwo(assetAllocation.bank)));
    }

    @Override
    public List<IdNameBean> findCurrentManagers(String code) {
        List<IdNameBean> res = new ArrayList<>();

        for(FundHistory fundManager:fundHistoryRepository.findAllByFundCodeAndAndEndDateIsNull(fillCode(code))){
            try {
                res.add(new IdNameBean(fundManager.getManagerId(),fundManager.getManagerName()));
            }catch (NullPointerException e){
                System.out.println(fundManager.getManagerId());
                //TODO
            }
        }

        return res;
    }

    @Override
    public List<ManagerHistoryBean> findHistoryManagerByCode(String code) {
        List<ManagerHistoryBean> res = new ArrayList<>();

        for(FundHistory fundHistory:fundHistoryRepository.findAllByFundCode(fillCode(code))){
            int difDays;
            if(fundHistory.getEndDate()!=null){
                difDays = DaysBetween.daysOfTwo(fundHistory.getStartDate(),fundHistory.getEndDate());
            }else {
                difDays = DaysBetween.daysOfTwo(fundHistory.getStartDate(), new Date());
            }
            res.add(new ManagerHistoryBean(fundHistory.getManagerId(), fundHistory.getManagerName(), sdf.format(fundHistory.getStartDate()), fundHistory.getEndDate()==null?null:sdf.format(fundHistory.getEndDate()), difDays,fundHistory.getPayback()));
        }
        return res;
    }

    @Override
    public List<ValueDateBean> findJensenByCode(String code) {
        return getAllGraphData(code,"0");
    }

    @Override
    public IdNameBean findFundCompanyByCode(String code) {
        FundCompany fundCompany = fundCompanyRepository.findByFundId(fillCode(code));
        if(fundCompany==null){
            return null;
        }
        String companyId = fundCompany.getCompanyId();
        String companyName = companyRepository.findByCompanyId(companyId).getName();
        return new IdNameBean(companyId,companyName);
    }

    @Override
    public List<FieldValueBean> findIndustryAttributionProfit(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'N');
        return ToFieldBean.factorResultToIndustryAttribution(factorResult);
    }

    @Override
    public List<FieldValueBean> findIndustryAttributionRisk(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'R');
        return ToFieldBean.factorResultToIndustryAttribution(factorResult);
    }

    @Override
    public List<FieldValueBean> findStyleAttributionProfit(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'N');
        return ToFieldBean.factorResultToStyleAttribution(factorResult);
    }

    @Override
    public List<FieldValueBean> findStyleAttributionRisk(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'R');
        return ToFieldBean.factorResultToStyleAttribution(factorResult);
    }

    /**
     * 计算基金近1、3、6、12、36月和成立以来的累计收益率
     *
     * @param code 基金代码
     * @return 各个阶段累计收益率
     */
    private RateBean getFundRate(String code) {
        //记录5个时间段
        Date dates[] = new Date[5];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //1
        calendar.add(Calendar.MONTH, -1);
        dates[0] = calendar.getTime();
        //3
        calendar.add(Calendar.MONTH, -2);
        dates[1] = calendar.getTime();
        //6
        calendar.add(Calendar.MONTH, -3);
        dates[2] = calendar.getTime();
        //12
        calendar.add(Calendar.MONTH, -6);
        dates[3] = calendar.getTime();
        //36
        calendar.add(Calendar.MONTH, -24);
        dates[4] = calendar.getTime();

        //从最后一天开始往前加，减少循环次数
        List<FundNetValue> netValues = fundNetValueRepository.findAllByCodeOrderByDateDesc(code);
        //记录已经到达的时间段
        int countDate = 0;
        //累计值
        double rate = 0;
        //各个阶段结果保存
        double rates[] = new double[6];

        for (FundNetValue netValue : netValues) {
            if (countDate != 5 && netValue.getDate().before(dates[countDate])) {
                rates[countDate] = fixToTwo(rate);
                countDate++;
            }
            if(netValue.getDailyRate()!=null) {
                rate += netValue.getDailyRate();
            }
        }
        //成立以来的收益率
        rates[5] = fixToTwo(rate);

        return new RateBean(rates);
    }

    /**
     * 不足6为则在前面补0
     * @param code 代码
     * @return
     */
    private String fillCode(String code){
        if(code==null){
            return "";
        }
        while (code.length()<6){
            code = "0"+code;
        }
        return code;
    }

    /**
     * 基金波动率
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findVolatility(String code) {
        return getAllGraphData(code,"3");
    }

    /**
     * 基金在险价值
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findValueAtRisk(String code) {
        return getAllGraphData(code,"4");
    }

    /**
     * 基金下行波动率
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findDownsideVolatility(String code) {
        return getAllGraphData(code,"5");
    }

    /**
     * 基金夏普指标
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findSharpeIndex(String code) {
        return getAllGraphData(code,"6");
    }

    /**
     * 基金特雷诺指标
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findTreynorIndex(String code) {
        return getAllGraphData(code,"7");
    }

    /**
     * 基金业绩持续性指标
     * @param code
     * @return
     */
    @Override
    public PerformanceIndexBean findPerformanceIndex(String code) {
        return new PerformanceIndexBean(cprRepository.findByFundId(code));
    }

    /**
     * 基金品种归因
     * @param code
     * @return
     */
    @Override
    public List<FieldValueBean> findVarietyAttribution(String code) {
        return ToFieldBean.brisonResultToVarietyAttribution(brisonResultRepository.findByCode(code));
    }

    /**
     * 基金Brison归因-基于股票持仓
     * @param code
     * @return
     */
    @Override
    public List<FieldValueBean> findBrisonAttributionStock(String code) {
        return ToFieldBean.stockBrisonResultToFieldValue(stockBrinsonResultRepository.findByFundId(code));
    }

    /**
     * 基金Brison归因-基于债券持仓
     * @param code
     * @return
     */
    @Override
    public List<FieldValueBean> findBrisonAttributionBond(String code) {
        BrisonResult brisonResult = brisonResultRepository.findByCode(code);
        return ToFieldBean.brisonResultToFieldValue(brisonResult);
    }


    /**
     * 基金择时择股能力
     * @param code
     * @return
     */
    @Override
    public List<ChooseBean> findChooseStockTime(String code) {
        return getStockTimeGraphData(code);
    }

    /**
     * 当前基金经理历任基金表现
     * @param code
     * @return
     */
    @Override
    public FundPerformanceBean findFundPerformance(String code) {
        List<PerformanceDataBean> funds = new ArrayList<>();
        List<PerformanceDataBean> others = new ArrayList<>();
        //现任基金经理
        for(FundHistory fundManager:fundHistoryRepository.findAllByFundCodeAndAndEndDateIsNull(code)){
            try{
                funds = addFundPerformOfManager(funds,fundManager.getManagerId());
            }catch (NullPointerException e){
                System.out.println(fundManager.getManagerId());
            }
        }
        //历史基金经理
        for(FundHistory fundHistory:fundHistoryRepository.findAllByFundCode(code)){
            try{
                funds = addFundPerformOfManager(funds,fundHistory.getManagerId());
            }catch (NullPointerException e){
                System.out.println(fundHistory.getManagerId());
            }
        }
        //其他基金
        for(Fund fund:fundRepository.findAll()){
            PerformanceDataBean res = new PerformanceDataBean(fund);
            if(!funds.contains(res)&&fund.getAnnualProfit()!=null){
                others.add(res);
            }
        }
        return new FundPerformanceBean(funds,others);
    }

    /**
     * 当前基金经理表现
     * @param code
     * @return
     */
    @Override
    public ManagerPerformanceBean findManagerPerformance(String code) {
        List<PerformanceDataBean> managers = new ArrayList<>();
        List<PerformanceDataBean> others = new ArrayList<>();
        for(FundHistory fundManager:fundHistoryRepository.findAllByFundCodeAndAndEndDateIsNull(fillCode(code))){
            Manager manager = managerRepository.findByManagerId(fundManager.getManagerId());
            if(manager!=null)
                managers.add(new PerformanceDataBean(manager));
        }
        for(Manager manager:managerRepository.findAll()){
            PerformanceDataBean res = new PerformanceDataBean(manager);
            if(!managers.contains(res)){
                others.add(res);
            }
        }
        return new ManagerPerformanceBean(managers,others);
    }

    /**
     * 基金舆情分析
     * @param code
     * @return
     */
    @Override
    public List<PublicOpinionBean> findPublicOpinion(String code) {
        //TODO 目前是拿一年内的数据
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR,-1);
        Date lastyear = calendar.getTime();

        List<PublicOpinionBean> res = new ArrayList<>();
        for(FundRemark fundRemark:fundRemarkRepository.findAllByCodeAndStartDateAfter(fillCode(code),lastyear)){
            List<NumBean> nums = new ArrayList<>();
            nums.add(new NumBean("正面评价",fundRemark.getPositive()));
            nums.add(new NumBean("负面评价",fundRemark.getNegative()));

            res.add(new PublicOpinionBean(sdf.format(fundRemark.getStartDate()),nums));
        }

        return res;
    }

    /**
     * 基金持仓关联网络图
     * @param code
     * @return
     */
    @Override
    public FundNetworkBean findPositionNetwork(String code) {
        List<NodeDataBean> nodes = new ArrayList<>();
        List<FundLinkDataBean> links = new ArrayList<>();

        code = fillCode(code);

        for(FundNetEdge fundNetEdge:getInterfacingCode(code, new ArrayList<>())){
            //记录两个点在node数组中的位置
            int indexA;
            int indexB;

            //先用id作为name，方便判断是否已经包含，最后一起转化为name
            NodeDataBean node = new NodeDataBean(fundNetEdge.getCodeIdA());
            int i = nodes.indexOf(node);
            if(i<0){
                nodes.add(node);
                indexA = nodes.size()-1;
            }else {
                indexA = i;
            }

            node = new NodeDataBean(fundNetEdge.getCodeIdB());
            i = nodes.indexOf(node);
            if(i<0){
                nodes.add(node);
                indexB = nodes.size()-1;
            }else {
                indexB = i;
            }
            links.add(new FundLinkDataBean(indexA,indexB,fundNetEdge.getWeight()));
        }

        //把id转化成name
        for(NodeDataBean node:nodes){
            try {
                node.name = fundRepository.findByCode(node.name).getName();
            }catch (NullPointerException e){
                System.out.println(node.name);
            }
        }

        return new FundNetworkBean(nodes,links);
    }

    private List<PerformanceDataBean> addFundPerformOfManager(List<PerformanceDataBean> list,String managerId){
        for(FundHistory fundManager:fundHistoryRepository.findAllByManagerIdAndAndEndDateIsNull(managerId)){
            Fund fund = fundRepository.findByCode(fundManager.getFundCode());
            PerformanceDataBean res = new PerformanceDataBean(fund);
            if(fund!=null&&fund.getAnnualProfit()!=null&&!list.contains(res)) {
                list.add(res);
            }
        }
        return list;
    }

    /**
     * 成立日以来的数据
     * @param code
     * @param attr
     * @return
     */
    private List<ValueDateBean> getAllGraphData(String code, String attr){
        if(!checkCodeExist(code)){
            return null;
        }
        List<ValueDateBean> res = new ArrayList<>();
        String pyRes = PythonUser.usePy("graphData.py", fillCode(code)+" "+"2000-01-01"+" "+sdf.format(new Date())+" "+attr);
        for(String line:pyRes.split("\n")){
            //处理每行
            String attrs[] = line.split(" ");
            res.add(new ValueDateBean(attrs[0], Double.valueOf(attrs[1])));
        }
        return res;
    }

    /**
     * 择时择股能力的数据
     * @param code
     * @return
     */
    private List<ChooseBean> getStockTimeGraphData(String code){
        if(!checkCodeExist(code)){
            return null;
        }
        List<ChooseBean> res = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR,-1);

        String pyRes = PythonUser.usePy("graphData.py", fillCode(code)+" "+sdf.format(calendar.getTime())+" "+sdf.format(new Date())+" 8");
        for(String line:pyRes.split("\n")){
            //处理每行
            String attrs[] = line.split(" ");
            //判断是否是日期行
            List<FieldValueBean> data = new ArrayList<>();
            data.add(new FieldValueBean("择股能力", fixToTwoAndPercent(Double.valueOf(attrs[1]))));
            data.add(new FieldValueBean("择时能力", fixToTwoAndPercent(Double.valueOf(attrs[2]))));
            data.add(new FieldValueBean("市场收益", fixToTwoAndPercent(Double.valueOf(attrs[3]))));

            res.add(new ChooseBean(attrs[0], data));
        }
        return res;
    }

    /**
     * 递归查找所有关系
     * @param code 代码
     * @param fundNetEdges 保存结果的数组
     * @return
     */
    private List<FundNetEdge> getInterfacingCode(String code,List<FundNetEdge> fundNetEdges){
        //作为左端点
        for(FundNetEdge fundNetEdge:fundNetEdgeRepository.findAllByCodeIdA(code)){
            if(!fundNetEdges.contains(fundNetEdge)) {
                fundNetEdges.add(fundNetEdge);
                fundNetEdges = getInterfacingCode(fundNetEdge.getCodeIdB(),fundNetEdges);
            }
        }
        //作为右端点
        for(FundNetEdge fundNetEdge:fundNetEdgeRepository.findAllByCodeIdB(code)){
            if(!fundNetEdges.contains(fundNetEdge)) {
                fundNetEdges.add(fundNetEdge);
                fundNetEdges = getInterfacingCode(fundNetEdge.getCodeIdB(),fundNetEdges);
            }
        }
        return fundNetEdges;
    }
}
