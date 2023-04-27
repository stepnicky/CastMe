package pl.coderslab.castme.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
public class CurrentUser extends User {
    private pl.coderslab.castme.user.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.castme.user.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.coderslab.castme.user.User getUser() {return user;}
    public void setUser(pl.coderslab.castme.user.User user) {
        this.user = user;
    }
}
