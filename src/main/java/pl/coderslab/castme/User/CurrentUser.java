package pl.coderslab.castme.User;


import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
public class CurrentUser extends User {
    private pl.coderslab.castme.User.User user;
    public CurrentUser(String username, String password,
                       Collection<? extends GrantedAuthority> authorities,
                       pl.coderslab.castme.User.User user) {
        super(username, password, authorities);
        this.user = user;
    }
    public pl.coderslab.castme.User.User getUser() {return user;}
    public void setUser(pl.coderslab.castme.User.User user) {
        this.user = user;
    }
}
