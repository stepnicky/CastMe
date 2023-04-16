package pl.coderslab.castme.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.castme.UserRole.UserRole;
import pl.coderslab.castme.UserRole.UserRoleService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;

    public UserController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

//    @GetMapping("/login")
//    public String loginForm(Model model) {
//        model.addAttribute("user", new User());
//        return "login-form";
//    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        List<UserRole> userRoles = userRoleService.getAllUserRoles();
        model.addAttribute("user", new User());
        model.addAttribute("userRoles", userRoles);
        return "registration-form";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid User user, BindingResult result, Model model) {
        User userExists = userService.findUserByEmail(user.getEmail());
        if(userExists != null) {
            result.rejectValue(
                    "email",
                    "There is already a user registered with the provided email address"
            );
        }
        if(result.hasErrors()) {
            List<UserRole> userRoles = userRoleService.getAllUserRoles();
            model.addAttribute("userRoles", userRoles);
            return "registration-form";
        }
        userService.saveUser(user);
        model.addAttribute("successMessage", "User has been registered successfully");
        model.addAttribute("user", new User());
        return "registration-form";
    }
}
