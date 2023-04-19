package pl.coderslab.castme.Actor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.castme.ActorRole.ActorRole;
import pl.coderslab.castme.ActorRole.ActorRoleService;
import pl.coderslab.castme.Agency.AgencyService;
import pl.coderslab.castme.Casting.Casting;
import pl.coderslab.castme.Casting.CastingService;
import pl.coderslab.castme.FeatureSet.FeatureSet;
import pl.coderslab.castme.FeatureSet.FeatureSetService;
import pl.coderslab.castme.Role.Role;
import pl.coderslab.castme.Skill.Skill;
import pl.coderslab.castme.Skill.SkillService;
import pl.coderslab.castme.User.CurrentUser;
import pl.coderslab.castme.User.User;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/actor")
@SessionAttributes({"notifications", "castings", "actorRoles"})
public class ActorController {

    private final ActorService actorService;
    private final SkillService skillService;
    private final AgencyService agencyService;
    private final FeatureSetService featureSetService;
    private final CastingService castingService;
    private final ActorRoleService actorRoleService;

    public ActorController(ActorService actorService,
                           SkillService skillService,
                           AgencyService agencyService,
                           FeatureSetService featureSetService,
                           CastingService castingService,
                           ActorRoleService actorRoleService) {
        this.actorService = actorService;
        this.skillService = skillService;
        this.agencyService = agencyService;
        this.featureSetService = featureSetService;
        this.castingService = castingService;
        this.actorRoleService = actorRoleService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        if (actor == null) {
            model.addAttribute("message", "Complete your actor's profile!");
            return "redirect:/actor/profile/form";
        }
        List<Casting> castings = castingService.getActiveCastingsByActorId(actor.getId());
        List<ActorRole> actorRoles = actorRoleService.getAllActorRolesByActor(actor);
        List<String> notifications = new ArrayList<>();
        for (Casting casting : castings) {
            casting.getRoles().forEach(r -> {
                actorRoles.forEach(ar -> {
                    if (ar.getRole().equals(r) &&
                            ar.getStatus().equals("invited")) {
                        notifications.add(String.format(
                                "<p class='message' data-actorRoleId='%s'>You have been invited to an audition for the role of " +
                                "<strong>%s</strong> in <strong>%s</strong><p>",
                                ar.getId(), r.getTitle(), casting.getTitle()
                        ));
                    }
                });
            });
        }
        model.addAttribute("notifications", notifications);
        model.addAttribute("castings", castings);
        model.addAttribute("actorRoles", actorRoles);
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
        if (!newSkill.isBlank()) {
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
    @PostMapping("/roles/viewed")
    @ResponseBody
    public void markRolesAsViewed(@RequestBody Map<String, List<Long>> actorRolesIdMap) {
        List<Long> actorRolesIds = actorRolesIdMap.get("numbers");
        actorRolesIds.forEach(id -> {
            ActorRole actorRole = actorRoleService.getActorRoleById(id);
            actorRole.setStatus("viewed");
            actorRoleService.updateActorRole(actorRole);
        });
    }
    @PostMapping("/role/like")
    public void likeRole(@RequestBody Map<String, String> actorRoleData) {
        Long id = Long.parseLong(actorRoleData.get("actorRoleId"));
        String action = actorRoleData.get("action");
        ActorRole actorRole = actorRoleService.getActorRoleById(id);
        actorRole.setStatus(action);
        actorRoleService.updateActorRole(actorRole);
    }
}
