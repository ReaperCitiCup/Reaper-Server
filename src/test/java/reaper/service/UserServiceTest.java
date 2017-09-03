package reaper.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import reaper.model.User;
import reaper.util.ResultMessage;

import static org.junit.Assert.*;

/**
 * Created by Sorumi on 17/9/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void signUp() {
        ResultMessage resultMessage = userService.signUp("123", "123");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }


    @Test
    public void signIn() {
        ResultMessage resultMessage = userService.signIn("111", "111");
        assertEquals(ResultMessage.SUCCESS, resultMessage);
    }
}