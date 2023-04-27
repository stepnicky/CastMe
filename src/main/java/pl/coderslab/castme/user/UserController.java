package pl.coderslab.castme.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.castme.castingdirector.CastingDirector;
import pl.coderslab.castme.castingdirector.CastingDirectorService;
import pl.coderslab.castme.userrole.UserRole;
import pl.coderslab.castme.userrole.UserRoleService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final CastingDirectorService castingDirectorService;

    public UserController(UserService userService, UserRoleService userRoleService, CastingDirectorService castingDirectorService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.castingDirectorService = castingDirectorService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        List<UserRole> userRoles = userRoleService.getAllUserRoles();
        model.addAttribute("user", new User());
        model.addAttribute("userRoles", userRoles);
        return "user/registration-form";
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
            return "user/registration-form";
        }
        userService.saveUser(user);
        if(user.getUserRole().getUserRole().equals("ROLE_CASTING_DIRECTOR")) {
            CastingDirector castingDirector = new CastingDirector();
            castingDirector.setUser(user);
            castingDirectorService.addCastingDirector(castingDirector);
        }
        model.addAttribute("successMessage", "User has been registered successfully");
        model.addAttribute("user", new User());
        return "user/registration-form";
    }

    @GetMapping("/user/account")
    public String userAccount(@AuthenticationPrincipal CurrentUser customUser,
                              Model model) {
        model.addAttribute("user", customUser.getUser());
        return "user/account";
    }

    @GetMapping("/user/account/edit")
    public String editAccountForm(@AuthenticationPrincipal CurrentUser customUser,
                                  Model model) {
        model.addAttribute("user", customUser.getUser());
        return "user/edit-form";
    }

    @PostMapping("/user/account/edit")
    public String editAccount(@AuthenticationPrincipal CurrentUser customUser,
                              @Valid User updatedUser,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "user/edit-form";
        }
        User currentUser = userService.findUserByEmail(customUser.getUsername());
        currentUser.setFirstName(updatedUser.getFirstName());
        currentUser.setLastName(updatedUser.getLastName());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setPhoneNumber(updatedUser.getPhoneNumber());
        userService.updateUser(currentUser);
        customUser.setUser(currentUser);
        return "redirect:/user/account";
    }
}
