package pl.coderslab.castme.Actor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.castme.Agency.AgencyService;
import pl.coderslab.castme.FeatureSet.FeatureSet;
import pl.coderslab.castme.FeatureSet.FeatureSetService;
import pl.coderslab.castme.Skill.Skill;
import pl.coderslab.castme.Skill.SkillService;
import pl.coderslab.castme.User.CurrentUser;
import pl.coderslab.castme.User.User;

import java.util.List;

@Controller
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;
    private final SkillService skillService;
    private final AgencyService agencyService;
    private final FeatureSetService featureSetService;

    public ActorController(ActorService actorService,
                           SkillService skillService,
                           AgencyService agencyService,
                           FeatureSetService featureSetService) {
        this.actorService = actorService;
        this.skillService = skillService;
        this.agencyService = agencyService;
        this.featureSetService = featureSetService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        if (actor == null) {
            model.addAttribute("message", "Fill in your actor's profile!");
            return "redirect:/actor/profile/form";
        }
        return "actor/dashboard";
    }

    @GetMapping("/profile/form")
    public String profileForm(Model model,
                              @ModelAttribute("message") String message) {
        model.addAttribute("actor", new Actor());
        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("agencies", agencyService.getAllAgencies());
        return "actor/form";
    }

    @PostMapping("/profile/form")
    public String processProfileForm(Actor actor,
                                     @RequestParam String gender,
                                     @RequestParam String height,
                                     @RequestParam String hairColor,
                                     @RequestParam String hairLength,
                                     @RequestParam String eyeColor,
                                     @RequestParam String figure,
                                     @RequestParam int age,
                                     @RequestParam String newSkill,
                                     @AuthenticationPrincipal CurrentUser customUser) {
        User user = customUser.getUser();
        actor.setUser(user);
        if(!newSkill.isBlank()) {
            Skill skill = new Skill();
            skill.setName(newSkill);
            skillService.createNewSkill(skill);
            List<Skill> skills = actor.getSkills();
            skills.add(skill);
            actor.setSkills(skills);
        }
        FeatureSet featureSet = new FeatureSet(
                gender, height, hairColor, hairLength,
                eyeColor, figure, age
        );
        featureSetService.createFeatureSet(featureSet);
        actor.setFeatureSet(featureSet);
        actorService.createActor(actor);
        return "redirect:/actor";
    }
}
