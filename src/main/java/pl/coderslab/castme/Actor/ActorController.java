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
import pl.coderslab.castme.Selftape.Selftape;
import pl.coderslab.castme.Selftape.SelftapeService;
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
    private final SelftapeService selftapeService;

    public ActorController(ActorService actorService,
                           SkillService skillService,
                           AgencyService agencyService,
                           FeatureSetService featureSetService,
                           CastingService castingService,
                           ActorRoleService actorRoleService,
                           StatusService statusService,
                           RoleService roleService,
                           SelftapeService selftapeService) {
        this.actorService = actorService;
        this.skillService = skillService;
        this.agencyService = agencyService;
        this.featureSetService = featureSetService;
        this.castingService = castingService;
        this.actorRoleService = actorRoleService;
        this.statusService = statusService;
        this.roleService = roleService;
        this.selftapeService = selftapeService;
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
    public String castingList(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        List<Casting> castings = castingService.getActiveCastingsByActorId(actor.getId());
        model.addAttribute("castings", castings);
        return "casting/list";
    }

    @GetMapping("/casting/{id}/details")
    public String castingDetails(@PathVariable Long id,
                                 @AuthenticationPrincipal CurrentUser customUser,
                                 Model model) {
        Casting casting = castingService.getCastingById(id);
        List<Role> roles = roleService.getAllRolesByCastingId(id);
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        List<Role> rolesByActor = roleService.getRolesByActorId(actor.getId());
        roles.retainAll(rolesByActor);
        for (Role r : roles) {
            Long numOfLikes = roleService.countStatusByRole(r.getId(), "liked");
            model.addAttribute(String.format("numOfLikes%s", r.getId()), numOfLikes);
        }
        model.addAttribute("casting", casting);
        model.addAttribute("roles", roles);
        return "casting/details";
    }

    @GetMapping("/role/{roleId}/details")
    public String roleDetails(@PathVariable Long roleId, Model model) {
        Casting casting = castingService.getCastingByRoleId(roleId);
        model.addAttribute("castingId", casting.getId());
        Role role = roleService.getRoleById(roleId);
        model.addAttribute("role", role);
        Long numOfLikes = roleService.countStatusByRole(roleId, "liked");
        model.addAttribute("numOfLikes", numOfLikes);
        FeatureSet featureSet = featureSetService.getFeatureSetByRoleId(roleId);
        model.addAttribute("featureSet", featureSet);
        List<Skill> skills = skillService.getSkillsByRoleId(roleId);
        model.addAttribute("skills", skills);
        List<Actor> actors = actorService.getActorsByRoleStatus(roleId, "liked");
        model.addAttribute("actors", actors);
        List<Selftape> selftapes = selftapeService.getSelftapesByRoleId(roleId);
        model.addAttribute("selftapes", selftapes);
        return "role/details";
    }

    @GetMapping("casting/archives")
    public String archivesList(@AuthenticationPrincipal CurrentUser customUser,
                               Model model) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        List<Casting> castings = castingService.getNonActiveCastingsByActorId(actor.getId());
        model.addAttribute("castings", castings);
        return "casting/archives";
    }

    @GetMapping("/casting/archives/{id}/details")
    public String archiveDetails(@PathVariable Long id,
                                 @AuthenticationPrincipal CurrentUser customUser,
                                 Model model) {
        Casting casting = castingService.getCastingById(id);
        List<Role> roles = roleService.getAllRolesByCastingId(id);
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        List<Role> rolesByActor = roleService.getRolesByActorId(actor.getId());
        roles.retainAll(rolesByActor);
        for (Role r : roles) {
            Long numOfLikes = roleService.countStatusByRole(r.getId(), "liked");
            model.addAttribute(String.format("numOfLikes%s", r.getId()), numOfLikes);
        }
        model.addAttribute("casting", casting);
        model.addAttribute("roles", roles);
        return "casting/archives-details";
    }
}
