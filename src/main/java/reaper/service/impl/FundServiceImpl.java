package reaper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reaper.bean.FundMiniBean;
import reaper.model.Fund;
import reaper.model.FundShortMessage;
import reaper.repository.FundRepository;
import reaper.repository.FundShortMessageRepository;
import reaper.service.FundService;

import java.util.ArrayList;
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
}
