package pl.coderslab.castme.Casting;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.castme.CastingDirector.CastingDirector;
import pl.coderslab.castme.CastingDirector.CastingDirectorService;
import pl.coderslab.castme.FeatureSet.FeatureSet;
import pl.coderslab.castme.FeatureSet.FeatureSetService;
import pl.coderslab.castme.Role.Role;
import pl.coderslab.castme.Role.RoleService;
import pl.coderslab.castme.Skill.SkillService;
import pl.coderslab.castme.User.CurrentUser;
import pl.coderslab.castme.User.User;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/casting")
public class CastingController {

    private final CastingService castingService;
    private final RoleService roleService;
    private final SkillService skillService;
    private final FeatureSetService featureSetService;
    private final CastingDirectorService castingDirectorService;

    public CastingController(CastingService castingService, RoleService roleService, SkillService skillService, FeatureSetService featureSetService, CastingDirectorService castingDirectorService) {
        this.castingService = castingService;
        this.roleService = roleService;
        this.skillService = skillService;
        this.featureSetService = featureSetService;
        this.castingDirectorService = castingDirectorService;
    }





    @GetMapping("/{castingId}/role/add")
    public String addRoleToCastingForm(@PathVariable Long castingId, Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("featureSet", new FeatureSet());
        model.addAttribute("skills", skillService.getAllSkills());
        return "role/form";
    }

    @PostMapping("/{castingId}/role/add")
    public String addRoleToCasting(@Valid Role role,
                                   BindingResult result,
                                   @PathVariable Long castingId,
                                   Model model,
                                   @RequestParam String gender,
                                   @RequestParam String height,
                                   @RequestParam String hairColor,
                                   @RequestParam String hairLength,
                                   @RequestParam String eyeColor,
                                   @RequestParam String figure,
                                   @RequestParam int ageFrom,
                                   @RequestParam int ageTo) {
        if(result.hasErrors()) {
            model.addAttribute("skills", skillService.getAllSkills());
            return "role/form";
        }
        int age = (ageFrom + ageTo)/2;
        FeatureSet featureSet = new FeatureSet(
                gender, height, hairColor,
                hairLength, eyeColor, figure, age
        );
        featureSetService.createFeatureSet(featureSet);
        role.setFeatureSet(featureSet);
        roleService.addNewRole(role);
        Casting casting = castingService.getCastingById(castingId);
        List<Role> roles = casting.getRoles();
        roles.add(role);
        casting.setRoles(roles);
        castingService.updateCasting(casting);
        return String.format("redirect:/director/casting/details/%s", castingId);
    }
}
