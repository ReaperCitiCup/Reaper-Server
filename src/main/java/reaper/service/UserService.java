package reaper.service;

import reaper.model.User;

/**
 * Created by Sorumi on 17/5/12.
 */
public interface UserService {

    public User findUserByUsername(String username);

    public String addUser(String name,String password);
}
