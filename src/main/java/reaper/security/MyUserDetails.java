package reaper.security;

import reaper.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by Sorumi on 17/5/12.
 */
public class MyUserDetails extends User implements UserDetails {

    public MyUserDetails(User user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        if(roles == null || roles.size() <1){
//            return AuthorityUtils.commaSeparatedStringToAuthorityList("");
//        }
//        StringBuilder commaBuilder = new StringBuilder();
//        for(UserRole role : roles){
//            commaBuilder.append(role.getRole()).append(",");
//        }
//        String authorities = commaBuilder.substring(0,commaBuilder.length()-1);
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

        return AuthorityUtils.commaSeparatedStringToAuthorityList("");
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
