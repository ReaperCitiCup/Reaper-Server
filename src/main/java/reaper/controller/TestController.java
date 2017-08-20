package reaper.controller;

import reaper.model.User;
import reaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Sorumi on 17/5/12.
 */
@Controller
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/users")
    @ResponseBody
    public User getUsers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return  userService.findUserByUsername(auth.getName());

    }

    @RequestMapping("/add")
    @ResponseBody
    public String addUser(String name,String password){
        return userService.addUser(name,password);
    }
}
