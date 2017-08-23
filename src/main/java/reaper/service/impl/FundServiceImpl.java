package reaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reaper.bean.FundMiniBean;
import reaper.bean.NetValueDateBean;
import reaper.model.FundNetValue;
import reaper.model.FundShortMessage;
import reaper.repository.FundNetValueRepository;
import reaper.repository.FundRepository;
import reaper.repository.FundShortMessageRepository;
import reaper.service.FundService;

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

    //TODO 日期格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public reaper.util.Page<FundMiniBean> findFundByKeyword(String keyword, String order, int size, int page) {
        reaper.util.Page<FundMiniBean> res =  new reaper.util.Page<FundMiniBean>();
        res.setOrder(order);
        res.setSize(size);
        res.setPage(page);
        //默认以升序进行排列
        org.springframework.data.domain.Page<FundShortMessage> fundPage = fundShortMessageRepository.findAllByNameLike("%"+keyword+"%",new PageRequest(page-1,size,new Sort(Sort.Direction.ASC,order)));
        List<FundMiniBean> miniBeans = new ArrayList<>();
        for(FundShortMessage fund:fundPage.getContent()){
            miniBeans.add(new FundMiniBean(fund.getCode(),fund.getName()));
        }
        res.setResult(miniBeans);
        res.setTotalCount((int)fundPage.getTotalElements());
        return res;
    }

    @Override
    public List<NetValueDateBean> findUnitNetValueTrendByCode(String code) {
        List<NetValueDateBean> res = new ArrayList<>();

        for (FundNetValue fundNetValue:fundNetValueRepository.findAllByCodeOrderByDateAsc(code)){
            res.add(new NetValueDateBean(sdf.format(fundNetValue.getDate()),fundNetValue.getUnitNetValue()));
        }
        return res;
    }

    @Override
    public List<NetValueDateBean> findCumulativeNetValueTrendByCode(String code) {
        List<NetValueDateBean> res = new ArrayList<>();

        for (FundNetValue fundNetValue:fundNetValueRepository.findAllByCodeOrderByDateAsc(code)){
            res.add(new NetValueDateBean(sdf.format(fundNetValue.getDate()),fundNetValue.getCumulativeNetValue()));
        }
        return res;
    }

    @Override
    public List<NetValueDateBean> findCumulativeRateTrendByCode(String code, String month) {
        List<NetValueDateBean> res = new ArrayList<>();

        //累加结果
        double cumulativeValue = 0;

        List<FundNetValue> fundNetValues;

        if(month.equals("all")){
            fundNetValues = fundNetValueRepository.findAllByCodeOrderByDateAsc(code);
        }else {
            //获得n月前的日期
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH,-Integer.valueOf(month));
            fundNetValues = fundNetValueRepository.findAllByCodeAndDateAfterOrderByDateAsc(code,calendar.getTime());
        }

        for (FundNetValue fundNetValue:fundNetValues){
            cumulativeValue += fundNetValue.getDailyRate();
            res.add(new NetValueDateBean(sdf.format(fundNetValue.getDate()),cumulativeValue));
        }
        return res;
    }
}
