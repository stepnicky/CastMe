package pl.coderslab.castme.UserRole;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;


public class UserRoleConverter implements Converter<String, UserRole> {

    @Autowired
    public UserRoleService userRoleService;

    @Override
    public UserRole convert(String source) {
        try {
            return userRoleService.getUserRoleById(Long.parseLong(source));
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
