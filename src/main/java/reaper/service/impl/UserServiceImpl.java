package reaper.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import reaper.bean.PasswordBean;
import reaper.model.User;
import reaper.repository.UserRepository;
import reaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reaper.util.ResultMessage;

/**
 * Created by Sorumi on 17/5/12.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResultMessage updateUser(User user) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(int id) {
        return null;
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = userRepository.findUserByUsername(auth.getName());
            return user;
        }
        return null;
    }

    @Override
    public ResultMessage signIn(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return ResultMessage.FAILED;
        }

        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage signOut() {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage resetPassword(PasswordBean passwordBean) {
        User user = getCurrentUser();
        if (user != null && user.getPassword().equals(passwordBean.oldPassword)) {
            user.setPassword(passwordBean.newPassword);
            userRepository.save(user);
            return ResultMessage.SUCCESS;
        }

        return ResultMessage.FAILED;
    }

    @Override
    public ResultMessage signUp(String username, String password) {
        User oldUser = userRepository.findUserByUsername(username);
        if (oldUser != null) {
            return ResultMessage.EXIST;
        }

        User user=new User(null,username,password);
        userRepository.save(user);

        userRepository.save(user);
        return ResultMessage.SUCCESS;
    }

//    @Override
//    public User findUserById(int code) {
//        return userRepository.findUserById(code);
//    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

}
