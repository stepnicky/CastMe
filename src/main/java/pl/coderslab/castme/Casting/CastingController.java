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
}
