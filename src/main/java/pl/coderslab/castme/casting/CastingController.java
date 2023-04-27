package pl.coderslab.castme.casting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.castme.castingdirector.CastingDirectorService;
import pl.coderslab.castme.featureset.FeatureSetService;
import pl.coderslab.castme.role.RoleService;
import pl.coderslab.castme.skill.SkillService;

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
