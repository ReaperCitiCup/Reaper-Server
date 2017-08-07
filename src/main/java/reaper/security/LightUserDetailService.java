package reaper.security;

import reaper.model.User;
import reaper.repository.UserRepository;
import reaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Sorumi on 17/5/12.
 */
@Service
public class LightUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = userService.findUserByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user find fail");
        }
        if (user == null) {
            throw new UsernameNotFoundException("no user found");

        } else {
            try {
                return new MyUserDetails(user);
            } catch (Exception e) {
                throw new UsernameNotFoundException("user role select fail");
            }
        }

    }
}
