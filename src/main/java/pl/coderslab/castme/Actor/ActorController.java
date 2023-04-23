package pl.coderslab.castme.Actor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.castme.ActorRole.ActorRole;
import pl.coderslab.castme.ActorRole.ActorRoleService;
import pl.coderslab.castme.ActorRoleStatus.Status;
import pl.coderslab.castme.ActorRoleStatus.StatusService;
import pl.coderslab.castme.Agency.AgencyService;
import pl.coderslab.castme.Casting.Casting;
import pl.coderslab.castme.Casting.CastingService;
import pl.coderslab.castme.FeatureSet.FeatureSet;
import pl.coderslab.castme.FeatureSet.FeatureSetService;
import pl.coderslab.castme.Role.Role;
import pl.coderslab.castme.Role.RoleService;
import pl.coderslab.castme.Skill.Skill;
import pl.coderslab.castme.Skill.SkillService;
import pl.coderslab.castme.User.CurrentUser;
import pl.coderslab.castme.User.User;

import java.util.ArrayList;
import java.util.List;

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
    private final StatusService statusService;
    private final RoleService roleService;

    public ActorController(ActorService actorService,
                           SkillService skillService,
                           AgencyService agencyService,
                           FeatureSetService featureSetService,
                           CastingService castingService,
                           ActorRoleService actorRoleService,
                           StatusService statusService,
                           RoleService roleService) {
        this.actorService = actorService;
        this.skillService = skillService;
        this.agencyService = agencyService;
        this.featureSetService = featureSetService;
        this.castingService = castingService;
        this.actorRoleService = actorRoleService;
        this.statusService = statusService;
        this.roleService = roleService;
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
        Status invited = statusService.getStatusByName("invited");
        List<String> notifications = new ArrayList<>();
        for (Casting casting : castings) {
            casting.getRoles().forEach(r -> {
                actorRoles.forEach(ar -> {
                    if (ar.getRole().equals(r) &&
                            ar.getStatuses().size() == 1 &&
                            ar.getStatuses().contains(invited)) {
                        notifications.add(String.format(
                                "<p class='message' data-actorRoleId='%s' data-action='viewed'>" +
                                "You have been invited to an audition for the role of " +
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
                                     @RequestParam int ageFrom,
                                     @RequestParam int ageTo,
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
                eyeColor, figure, ageFrom, ageTo
        );
        featureSetService.createFeatureSet(featureSet);
        actor.setFeatureSet(featureSet);
        actorService.createActor(actor);
        return "redirect:/actor";
    }
    @GetMapping("/casting/list")
    public String castingDetails(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        List<Casting> castings = castingService.getActiveCastingsByActorId(actor.getId());
        model.addAttribute("castings", castings);
        return "casting/list";
    }

}
