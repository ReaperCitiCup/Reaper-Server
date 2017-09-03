package reaper.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import reaper.model.User;
import reaper.service.UserService;
import reaper.util.ResultMessage;

import java.util.Collections;

/**
 * Created by Sorumi on 17/5/13.
 */

@Component(value = "authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("Username ot exist");
        }

        // 认证逻辑
        if (userService.signIn(username, password) == ResultMessage.SUCCESS) {

            // 这里设置权限和角色
//            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//            authorities.add( new GrantedAuthorityImpl("ROLE_ADMIN") );
//            authorities.add( new GrantedAuthorityImpl("AUTH_WRITE") );
            // 生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
            return auth;
        } else {
            throw new BadCredentialsException("Check your password");
        }
    }

    // 是否可以提供输入类型的认证服务
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
