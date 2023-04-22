package pl.coderslab.castme.CastingDirector;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Actor.ActorService;
import pl.coderslab.castme.ActorRole.ActorRole;
import pl.coderslab.castme.ActorRole.ActorRoleService;
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

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/director")
public class CastingDirectorController {

    private final CastingService castingService;
    private final CastingDirectorService castingDirectorService;
    private final RoleService roleService;
    private final ActorRoleService actorRoleService;
    private final FeatureSetService featureSetService;
    private final SkillService skillService;
    private final ActorService actorService;
    private final SelftapeService selftapeService;

    public CastingDirectorController(CastingService castingService,
                                     CastingDirectorService castingDirectorService,
                                     RoleService roleService,
                                     ActorRoleService actorRoleService,
                                     FeatureSetService featureSetService,
                                     SkillService skillService,
                                     ActorService actorService, SelftapeService selftapeService) {
        this.castingService = castingService;
        this.castingDirectorService = castingDirectorService;
        this.roleService = roleService;
        this.actorRoleService = actorRoleService;
        this.featureSetService = featureSetService;
        this.skillService = skillService;
        this.actorService = actorService;
        this.selftapeService = selftapeService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(user);
        List<Casting> castings = castingService.getCastingsByCastingDirector(castingDirector.getId());
        castings.forEach(c -> {
            Long numOfLikes = castingService.countCastingStatuses(castingDirector.getId(), c.getId(), "accepted");
            numOfLikes += castingService.countCastingStatuses(castingDirector.getId(), c.getId(), "viewedByCastingDirector");
            model.addAttribute(String.format("numOfLikes%s", c.getId()), numOfLikes);
            Long numOfSelftapes = castingService.countCastingStatuses(castingDirector.getId(), c.getId(), "completed");
            model.addAttribute(String.format("numOfSelftapes%s", c.getId()), numOfSelftapes);
            LocalDate now = LocalDate.now();
            LocalDate deadline = c.getDeadline();
            Integer daysTillDeadline = Period.between(now, deadline).getDays();
            model.addAttribute(String.format("daysTillDeadline%s", c.getId()), daysTillDeadline);
        });
        List<ActorRole> actorRoles = actorRoleService.getActorRolesByStatus("accepted");
        List<String> notifications = new ArrayList<>();
        actorRoles.forEach(ar -> {
            User actorUser = ar.getActor().getUser();
            notifications.add(String.format(
                    "<p class='message' data-actorRoleId='%s'><strong>%s %s</strong> " +
                            "likes the role of %s<p>",
                    ar.getId(), actorUser.getFirstName(),
                    actorUser.getLastName(), ar.getRole().getTitle()
            ));
        });
        model.addAttribute("notifications", notifications);
        model.addAttribute("castings", castings);
        return "casting-director/dashboard";
    }

    @GetMapping("/casting/add")
    public String addCastingForm(Model model) {
        model.addAttribute("casting", new Casting());
        model.addAttribute("title", "Add new casting");
        return "casting/form";
    }

    @PostMapping("/casting/add")
    public String addCasting(@Valid Casting casting,
                             BindingResult result,
                             @AuthenticationPrincipal CurrentUser customUser) {
        if(result.hasErrors()) {
            return "casting/form";
        }
        casting.setActive(true);
        castingService.createCasting(casting);
        User user = customUser.getUser();
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(user);
        List<Casting> directorsCastings = castingDirector.getCastings();
        directorsCastings.add(casting);
        castingDirector.setCastings(directorsCastings);
        castingDirectorService.updateCastingDirector(castingDirector);
        return String.format("redirect:/director/casting/%s/details", casting.getId());
    }
    @GetMapping("/casting/{id}/details")
    public String castingDetails(@PathVariable Long id, Model model) {
        Casting casting = castingService.getCastingById(id);
        List<Role> roles = roleService.getAllRolesByCasting(id);
        roles.forEach(r -> {
            Long numOfLikes = roleService.countStatusByRole(r.getId(), "accepted");
            numOfLikes += roleService.countStatusByRole(r.getId(), "viewedByCastingDirector");
            model.addAttribute(String.format("numOfLikes%s", r.getId()), numOfLikes);
        });
        model.addAttribute("casting", casting);
        model.addAttribute("roles", roles);
        return "casting/details";
    }
    @GetMapping("/casting/{castingId}/role/add")
    public String addRoleToCastingForm(@PathVariable Long castingId, Model model) {
        model.addAttribute("title", "Add new role");
        model.addAttribute("role", new Role());
        model.addAttribute("featureSet", new FeatureSet());
        model.addAttribute("skills", skillService.getAllSkills());
        return "role/form";
    }

    @PostMapping("/casting/{castingId}/role/add")
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
        FeatureSet featureSet = new FeatureSet(
                gender, height, hairColor,
                hairLength, eyeColor, figure, ageFrom, ageTo
        );
        featureSetService.createFeatureSet(featureSet);
        role.setFeatureSet(featureSet);
        roleService.addNewRole(role);
        Casting casting = castingService.getCastingById(castingId);
        List<Role> roles = casting.getRoles();
        roles.add(role);
        casting.setRoles(roles);
        castingService.updateCasting(casting);
        return String.format("redirect:/director/casting/%s/details", castingId);
    }
    @GetMapping("/role/{roleId}/edit")
    public String editRoleForm(@PathVariable Long roleId, Model model) {
        model.addAttribute("title", "Edit role");
        model.addAttribute("role", roleService.getRoleById(roleId));
        model.addAttribute("featureSet", featureSetService.getFeatureSetByRoleId(roleId));
        model.addAttribute("skills", skillService.getAllSkills());
        return "role/form";
    }
    @PostMapping("/role/{roleId}/edit")
    public String editRole(@Valid Role role,
                           BindingResult result,
                           @PathVariable Long roleId,
                           Model model,
                           @RequestParam Long featureSetId,
                           @RequestParam String gender,
                           @RequestParam String height,
                           @RequestParam String hairColor,
                           @RequestParam String hairLength,
                           @RequestParam String eyeColor,
                           @RequestParam String figure,
                           @RequestParam int ageFrom,
                           @RequestParam int ageTo) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Edit role");
            model.addAttribute("role", roleService.getRoleById(roleId));
            model.addAttribute("featureSet", featureSetService.getFeatureSetByRoleId(roleId));
            model.addAttribute("skills", skillService.getAllSkills());
            return "role/form";
        }
        role.setId(roleId);
        FeatureSet featureSet = featureSetService.getFeatureSetById(featureSetId);
        featureSet.setGender(gender);
        featureSet.setHeight(height);
        featureSet.setHairColor(hairColor);
        featureSet.setHairLength(hairLength);
        featureSet.setEyeColor(eyeColor);
        featureSet.setFigure(figure);
        featureSet.setAgeFrom(ageFrom);
        featureSet.setAgeTo(ageTo);
        featureSetService.updateFeatureSet(featureSet);
        role.setFeatureSet(featureSet);
        roleService.updateRole(role);
        Casting casting = castingService.getCastingByRoleId(roleId);
        return String.format("redirect:/director/casting/%s/details", casting.getId());
    }
    @GetMapping("/role/{roleId}/details")
    public String roleDetails(@PathVariable Long roleId, Model model) {
        Casting casting = castingService.getCastingByRoleId(roleId);
        model.addAttribute("castingId", casting.getId());
        Role role = roleService.getRoleById(roleId);
        model.addAttribute("role", role);
        Long numOfLikes = roleService.countStatusByRole(roleId, "accepted");
        numOfLikes += roleService.countStatusByRole(roleId, "viewedByCastingDirector");
        model.addAttribute("numOfLikes", numOfLikes);
        FeatureSet featureSet = featureSetService.getFeatureSetByRoleId(roleId);
        model.addAttribute("featureSet", featureSet);
        List<Skill> skills = skillService.getSkillsByRoleId(roleId);
        model.addAttribute("skills", skills);
        List<Actor> actors = actorService.getActorsByRoleStatus(roleId, "accepted");
        actors.addAll(actorService.getActorsByRoleStatus(roleId, "viewedByCastingDirector"));
        model.addAttribute("actors", actors);
        List<Selftape> selftapes = selftapeService.getSelftapesByRoleId(roleId);
        model.addAttribute("selftapes", selftapes);
        return "role/details";
    }
    @GetMapping("/role/{roleId}/delete")
    public String deleteRole(@PathVariable Long roleId) {
        Casting casting = castingService.getCastingByRoleId(roleId);
        actorRoleService.deleteActorRolesByRoleId(roleId);
        roleService.deleteRole(roleId);
        return String.format("redirect:/director/casting/%s/details", casting.getId());
    }
}
