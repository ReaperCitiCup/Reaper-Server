package reaper.service;

import reaper.bean.FundMiniBean;
import reaper.util.Page;

/**
 * Created by Sorumi on 17/8/21.
 */
public interface FundService {


    /**
     * @param keyword  关键字
     * @param order    按什么排序
     * @param size     每页大小
     * @param page     第几页
     * @return Movie 分页列表
     */
    public Page<FundMiniBean> findFundByKeyword(String keyword, String order, int size, int page);
}
