package reaper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import reaper.bean.PasswordBean;
import reaper.bean.ResultMessageBean;
import reaper.model.User;
import reaper.service.UserService;
import reaper.util.ResultMessage;

/**
 * Created by Sorumi on 17/9/3.
 */

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/test")
    @ResponseBody
    public ResultMessageBean test() {
        return new ResultMessageBean(true, "Success");

    }

    /**
     * 注册
     *
     * @param user 用户名和密码
     * @return 注册结果，是否成功
     */
    @ResponseBody
    @RequestMapping(
            value = "/user/sign-up",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean signUp(
            @RequestBody User user) {
        ResultMessage resultMessage = userService.signUp(user.getUsername(), user.getPassword());
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        } else if (resultMessage == ResultMessage.EXIST) {
            result.message = "该用户名已存在!";
        }
        return result;
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/user/sign-in",
            method = RequestMethod.POST)
    public ResultMessageBean signIn(
            @RequestBody User user) {
        ResultMessage resultMessage = userService.signIn(user.getUsername(), user.getPassword());
        ResultMessageBean result = new ResultMessageBean(false);
        if (resultMessage == ResultMessage.SUCCESS) {
            result.result = true;
        }else if (resultMessage == ResultMessage.FAILED) {
            result.message = "密码错误!";
        }
        return result;
    }

    /**
     * 注销
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/user/sign-out",
            method = RequestMethod.POST
    )
    public ResultMessageBean signout() {
        return new ResultMessageBean(true);
    }

    /**
     * 当前登录用户
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET)
    public User getCurrentUser() {
       return userService.getCurrentUser();
    }

    /**
     * 修改密码
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(
            value = "/user/password",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ResultMessageBean editPassword(@RequestBody PasswordBean password) {
        ResultMessage resultMessage = userService.resetPassword(password);
        boolean result = resultMessage == ResultMessage.SUCCESS;
        String message = "";
        if (!result) {
            message = "修改失败";
        }
        return new ResultMessageBean(result, message);
    }


}
