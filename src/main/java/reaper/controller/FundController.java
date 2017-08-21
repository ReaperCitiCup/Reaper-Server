package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reaper.bean.FundMiniBean;
import reaper.service.FundService;
import reaper.util.Page;

/**
 * Created by Sorumi on 17/8/21.
 */

@Controller
@RequestMapping("/api/fund")
public class FundController {

    @Autowired
    private FundService fundService;


    /**
     * @param keyword  关键字
     * @param order    按什么排序
     * @param size     每页大小
     * @param page     第几页
     * @return Movie 分页列表
     */
    @ResponseBody
    @RequestMapping(
            value = "/search",
            params = {"keyword", "order", "size", "page"},
            method = RequestMethod.GET,
            produces = {"application/json; charset=UTF-8"})
    public Page<FundMiniBean> findMoviesByKeyword(@RequestParam(value = "keyword") String keyword,
                                                  @RequestParam(value = "order") String order,
                                                  @RequestParam(value = "size") int size,
                                                  @RequestParam(value = "page") int page) {
        return fundService.findFundByKeyword(keyword, order, size, page);
    }
}
