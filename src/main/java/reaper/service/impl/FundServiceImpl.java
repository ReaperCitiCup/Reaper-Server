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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    FundManagerRepository fundManagerRepository;

    @Autowired
    FundCompanyRepository fundCompanyRepository;

    @Autowired
    AssetAllocationRepository assetAllocationRepository;

    @Autowired
    FactorResultRepository factorResultRepository;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private DecimalFormat decimalFormat = new DecimalFormat("#.00");

    @Override
    public reaper.util.Page<FundMiniBean> findFundByKeyword(String keyword, String order, int size, int page) {
        reaper.util.Page<FundMiniBean> res = new reaper.util.Page<>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);
        //默认以升序进行排列
        org.springframework.data.domain.Page<Fund> fundPage = fundRepository.findAllByNameLike("%" + keyword + "%", new PageRequest(page - 1, size, new Sort(Sort.Direction.ASC, order==null?"code":order)));
        List<FundMiniBean> miniBeans = new ArrayList<>();
        for (Fund fund : fundPage.getContent()) {
            //根据基金代码找到经理代码
            List<FundManager> fundManagers = fundManagerRepository.findByFundCode(fund.getCode());
            //根据经理代码找到经理名
            List<MiniBean> managerList = new ArrayList<>();
            for(FundManager fundManager:fundManagers){
                managerList.add(new MiniBean(fundManager.getManagerId(),managerRepository.findByManagerId(fundManager.getManagerId()).getName()));
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
        Fund fund = fundRepository.findByCode(fillCode(code));
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

        FundNetValue fundNetValue = fundNetValueRepository.findFirstByCodeOrderByDateDesc(code);
        RateBean rateBean = getFundRate(code);

        List<MiniBean> managers = new ArrayList<>();
        for(FundManager fundManager:fundManagerRepository.findByFundCode(code)){
            managers.add(new MiniBean(fundManager.getManagerId(), managerRepository.findByManagerId(fundManager.getManagerId()).getName()));
        }
        String id = fundCompanyRepository.findByFundId(code).getCompanyId();
        MiniBean company = new MiniBean(id, companyRepository.findByCompanyId(id).getName());
        return fundModelToBean.modelToBean(fund, fundNetValue, rateBean, managers, company);
    }

    @Override
    public List<ValueDateBean> findUnitNetValueTrendByCode(String code) {
        List<ValueDateBean> res = new ArrayList<>();

        for (FundNetValue fundNetValue : fundNetValueRepository.findAllByCodeOrderByDateAsc(fillCode(code))) {
            res.add(new ValueDateBean(sdf.format(fundNetValue.getDate()), fundNetValue.getUnitNetValue()));
        }
        return res;
    }

    @Override
    public List<ValueDateBean> findCumulativeNetValueTrendByCode(String code) {
        List<ValueDateBean> res = new ArrayList<>();

        for (FundNetValue fundNetValue : fundNetValueRepository.findAllByCodeOrderByDateAsc(fillCode(code))) {
            res.add(new ValueDateBean(sdf.format(fundNetValue.getDate()), fundNetValue.getCumulativeNetValue()));
        }
        return res;
    }

    @Override
    public List<ValueDateBean> findCumulativeRateTrendByCode(String code, String month) {
        List<ValueDateBean> res = new ArrayList<>();

        //累加结果
        double cumulativeValue = 0;

        List<FundNetValue> fundNetValues;

        if (month.equals("all")) {
            fundNetValues = fundNetValueRepository.findAllByCodeOrderByDateAsc(fillCode(code));
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
            cumulativeValue += fundNetValue.getDailyRate();
            res.add(new ValueDateBean(sdf.format(fundNetValue.getDate()), cumulativeValue));
        }

        return res;
    }

    @Override
    public List<HistoryManagerBean> findHistoryManagersByCode(String code) {
        List<HistoryManagerBean> res = new ArrayList<>();

        for (FundHistory fundHistory : fundHistoryRepository.findAllByFundCodeOrderByStartDateAsc(fillCode(code))) {
            //计算相差的天数
            int difDays = DaysBetween.daysOfTwo(fundHistory.getStartDate(),fundHistory.getEndDate());
            res.add(new HistoryManagerBean(fundHistory.getManagerId(), managerRepository.findByManagerId(fundHistory.getManagerId()).getName(), sdf.format(fundHistory.getStartDate()), sdf.format(fundHistory.getEndDate()), difDays, fundHistory.getPayback()));
        }
        return res;
    }

    @Override
    public CurrentAssetBean findCurrentAssetByCode(String code) {
        AssetAllocation assetAllocation = assetAllocationRepository.findByCode(fillCode(code));
        return assetAllocation==null?null:new CurrentAssetBean(Double.valueOf(decimalFormat.format(assetAllocation.bond)),Double.valueOf(decimalFormat.format(assetAllocation.stock)),Double.valueOf(decimalFormat.format(assetAllocation.bank)));
    }

    @Override
    public List<ManagerHistoryBean> findHistoryManagerByCode(String code) {
        List<ManagerHistoryBean> res = new ArrayList<>();

        for(FundHistory fundHistory:fundHistoryRepository.findAllByFundCode(fillCode(code))){
            //找到对应经理名字
            Manager manager = managerRepository.findByManagerId(fundHistory.getManagerId());
            int difDays;
            if(fundHistory.getEndDate()!=null){
                difDays = DaysBetween.daysOfTwo(fundHistory.getStartDate(),fundHistory.getEndDate());
            }else {
                difDays = DaysBetween.daysOfTwo(fundHistory.getStartDate(), new Date());
            }
            res.add(new ManagerHistoryBean(fundHistory.getManagerId(), manager.getName(), sdf.format(fundHistory.getStartDate()), fundHistory.getEndDate()==null?null:sdf.format(fundHistory.getEndDate()), difDays,fundHistory.getPayback()));
        }
        return res;
    }

    @Override
    public List<ValueDateBean> findJensenByCode(String code) {
        List<ValueDateBean> res = new ArrayList<>();
        String pyRes = PythonUser.usePy("alpha.py", fillCode(code));
        for(String line:pyRes.split("\n")){
            //处理每行
            String attrs[] = line.split(" ");
            //判断是否是日期行
            if(attrs[0].startsWith("2")) {
                res.add(new ValueDateBean(attrs[0], Double.valueOf(attrs[1])));
            }
        }
        return res;
    }

    @Override
    public MiniBean findFundCompanyByCode(String code) {
        FundCompany fundCompany = fundCompanyRepository.findByFundId(fillCode(code));
        if(fundCompany==null){
            return null;
        }
        String companyId = fundCompany.getCompanyId();
        String companyName = companyRepository.findByCompanyId(companyId).getName();
        return new MiniBean(companyId,companyName);
    }

    @Override
    public List<FieldValueBean> findIndustryAttributionProfit(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'N');
        return factorResult==null?null:new ToFieldBean().factorResultToIndustryAttribution(factorResult);
    }

    @Override
    public List<FieldValueBean> findIndustryAttributionRisk(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'R');
        return factorResult==null?null:new ToFieldBean().factorResultToIndustryAttribution(factorResult);
    }

    @Override
    public List<FieldValueBean> findStyleAttributionProfit(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'N');
        return factorResult==null?null:new ToFieldBean().factorResultToStyleAttribution(factorResult);
    }

    @Override
    public List<FieldValueBean> findStyleAttributionRisk(String code) {
        FactorResult factorResult = factorResultRepository.findByCodeAndFactorType(fillCode(code),'R');
        return factorResult==null?null:new ToFieldBean().factorResultToStyleAttribution(factorResult);
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
                rates[countDate] = Double.parseDouble(decimalFormat.format(rate));
                countDate++;
            }
            if(netValue.getDailyRate()!=null) {
                rate += netValue.getDailyRate();
            }
        }
        //成立以来的收益率
        rates[5] = Double.parseDouble(decimalFormat.format(rate));

        return new RateBean(rates);
    }

    /**
     * 不足6为则在前面补0
     * @param code 代码
     * @return
     */
    private String fillCode(String code){
        while (code.length()<6){
            code = "0"+code;
        }
        return code;
    }

    /**
     * 基金风险走势
     * @param code 代码
     * @return
     */
    @Override
    public List<ValueDateBean> findRiskTrend(String code) {
        return null;
    }

    /**
     * 基金每日回撤
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findDailyRetracement(String code) {
        return null;
    }

    /**
     * 基金波动率
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findVolatility(String code) {
        return null;
    }

    /**
     * 基金在险价值
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findValueAtRisk(String code) {
        return null;
    }

    /**
     * 基金下行波动率
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findDownsideVolatility(String code) {
        return null;
    }

    /**
     * 基金夏普指标
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findSharpeIndex(String code) {
        return null;
    }

    /**
     * 基金特雷诺指标
     * @param code
     * @return
     */
    @Override
    public List<ValueDateBean> findTreynorIndex(String code) {
        return null;
    }

    /**
     * 基金业绩持续性指标
     * @param code
     * @return
     */
    @Override
    public PerformanceIndexBean findPerformanceIndex(String code) {
        return null;
    }

    /**
     * 基金品种归因
     * @param code
     * @return
     */
    @Override
    public List<FieldValueBean> findVarietyAttribution(String code) {
        return null;
    }

    /**
     * 基金Brison归因-基于股票持仓
     * @param code
     * @return
     */
    @Override
    public List<FieldValueBean> findBrisonAttributionStock(String code) {
        return null;
    }

    /**
     * 基金Brison归因-基于债券持仓
     * @param code
     * @return
     */
    @Override
    public List<FieldValueBean> findBrisonAttributionBond(String code) {
        return null;
    }

    /**
     * 基金择时能力
     * @param code
     * @return
     */
    @Override
    public ChooseBean findChooseTime(String code) {
        return null;
    }

    /**
     * 基金择股能力
     * @param code
     * @return
     */
    @Override
    public ChooseBean findChooseStock(String code) {
        return null;
    }

    /**
     * 当前基金经理历任基金表现
     * @param code
     * @return
     */
    @Override
    public FundPerformanceBean findFundPerformance(String code) {
        return null;
    }

    /**
     * 当前基金经理表现
     * @param code
     * @return
     */
    @Override
    public ManagerPerformanceBean findManagerPerformance(String code) {
        return null;
    }

    /**
     * 基金舆情分析
     * @param code
     * @return
     */
    @Override
    public List<PublicOpinionBean> findPublicOpinion(String code) {
        return null;
    }

    /**
     * 基金持仓关联网络图
     * @param code
     * @return
     */
    @Override
    public List<NetworkBean> findPositionNetwork(String code) {
        return null;
    }
}
