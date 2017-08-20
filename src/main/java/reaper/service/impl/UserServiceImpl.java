package reaper.service.impl;

import reaper.model.User;
import reaper.repository.UserRepository;
import reaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sorumi on 17/5/12.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public String addUser(String name, String password) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        userRepository.save(user);
        return "success";
    }
}
