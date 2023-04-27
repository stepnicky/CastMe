package pl.coderslab.castme.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.castme.userrole.UserRole;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmail(email);
        List<GrantedAuthority> authorities = getUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }
    private List<GrantedAuthority> getUserAuthority(UserRole userRole) {
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(userRole.getUserRole()));
        return roles;
    }
    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new CurrentUser(user.getEmail(), user.getPassword(), authorities, user);
    }
}
