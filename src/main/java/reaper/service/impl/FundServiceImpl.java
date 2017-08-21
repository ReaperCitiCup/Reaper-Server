package reaper.service.impl;

import org.springframework.stereotype.Service;
import reaper.bean.FundMiniBean;
import reaper.service.FundService;
import reaper.util.Page;

/**
 * Created by Sorumi on 17/8/21.
 */

@Service
public class FundServiceImpl implements FundService {

    @Override
    public Page<FundMiniBean> findFundByKeyword(String keyword, String order, int size, int page) {
        return new Page<FundMiniBean>();
    }
}
