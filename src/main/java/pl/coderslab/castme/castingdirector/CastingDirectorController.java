package pl.coderslab.castme.castingdirector;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.castme.actor.Actor;
import pl.coderslab.castme.actor.ActorService;
import pl.coderslab.castme.actorrole.ActorRole;
import pl.coderslab.castme.actorrole.ActorRoleService;
import pl.coderslab.castme.actorrolestatus.Status;
import pl.coderslab.castme.actorrolestatus.StatusService;
import pl.coderslab.castme.agency.Agency;
import pl.coderslab.castme.agency.AgencyService;
import pl.coderslab.castme.casting.Casting;
import pl.coderslab.castme.casting.CastingService;
import pl.coderslab.castme.featureset.FeatureSet;
import pl.coderslab.castme.featureset.FeatureSetService;
import pl.coderslab.castme.photo.Photo;
import pl.coderslab.castme.photo.PhotoService;
import pl.coderslab.castme.role.Role;
import pl.coderslab.castme.role.RoleService;
import pl.coderslab.castme.roleattachment.RoleAttachment;
import pl.coderslab.castme.roleattachment.RoleAttachmentService;
import pl.coderslab.castme.selftape.Selftape;
import pl.coderslab.castme.selftape.SelftapeService;
import pl.coderslab.castme.skill.Skill;
import pl.coderslab.castme.skill.SkillService;
import pl.coderslab.castme.user.CurrentUser;
import pl.coderslab.castme.user.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/director")
@SessionAttributes({"notifications"})
public class CastingDirectorController {

    private final CastingService castingService;
    private final CastingDirectorService castingDirectorService;
    private final RoleService roleService;
    private final ActorRoleService actorRoleService;
    private final FeatureSetService featureSetService;
    private final SkillService skillService;
    private final ActorService actorService;
    private final SelftapeService selftapeService;
    private final StatusService statusService;
    private final PhotoService photoService;
    private final RoleAttachmentService roleAttachmentService;
    private final AgencyService agencyService;

    public CastingDirectorController(CastingService castingService,
                                     CastingDirectorService castingDirectorService,
                                     RoleService roleService,
                                     ActorRoleService actorRoleService,
                                     FeatureSetService featureSetService,
                                     SkillService skillService,
                                     ActorService actorService,
                                     SelftapeService selftapeService,
                                     StatusService statusService,
                                     PhotoService photoService,
                                     RoleAttachmentService roleAttachmentService,
                                     AgencyService agencyService) {
        this.castingService = castingService;
        this.castingDirectorService = castingDirectorService;
        this.roleService = roleService;
        this.actorRoleService = actorRoleService;
        this.featureSetService = featureSetService;
        this.skillService = skillService;
        this.actorService = actorService;
        this.selftapeService = selftapeService;
        this.statusService = statusService;
        this.photoService = photoService;
        this.roleAttachmentService = roleAttachmentService;
        this.agencyService = agencyService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(user);
        List<Casting> castings = castingService.getActiveCastingsByCastingDirectorId(castingDirector.getId());
        castings.forEach(c -> {
            Long numOfLikes = castingService.countCastingStatuses( c.getId(), "liked");
            model.addAttribute(String.format("numOfLikes%s", c.getId()), numOfLikes);
            Long numOfSelftapes = castingService.countCastingStatuses(c.getId(), "completed");
            model.addAttribute(String.format("numOfSelftapes%s", c.getId()), numOfSelftapes);
            LocalDate now = LocalDate.now();
            LocalDate deadline = c.getDeadline();
            Integer daysTillDeadline = Period.between(now, deadline).getDays();
            model.addAttribute(String.format("daysTillDeadline%s", c.getId()), daysTillDeadline);
        });
        Status liked = statusService.getStatusByName("liked");
        List<ActorRole> likedActorRoles = actorRoleService.getActorRolesByStatus(liked);
        Status likeViewed = statusService.getStatusByName("likeViewedByCastingDirector");
        likedActorRoles.removeIf(ar -> ar.getStatuses().contains(likeViewed));
        List<String> notifications = new ArrayList<>();
        likedActorRoles.forEach(ar -> {
            User actorUser = ar.getActor().getUser();
            notifications.add(String.format(
                    "<p class='message' data-actorRoleId='%s'><strong>%s %s</strong> " +
                            "likes the role of %s<p>",
                    ar.getId(), actorUser.getFirstName(),
                    actorUser.getLastName(), ar.getRole().getTitle()
            ));
        });
        Status completed = statusService.getStatusByName("completed");
        List<ActorRole> completedActorRoles = actorRoleService.getActorRolesByStatus(completed);
        Status selftapeViewed = statusService.getStatusByName("selftapeViewed");
        completedActorRoles.removeIf(ar -> ar.getStatuses().contains(selftapeViewed));
        completedActorRoles.forEach(ar -> {
            User actorUser = ar.getActor().getUser();
            notifications.add(String.format(
                    "<p class='message' data-actorRoleId='%s'><strong>%s %s</strong> " +
                            "has sent you a selftape for the role of %s<p>",
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
                             Model model,
                             @AuthenticationPrincipal CurrentUser customUser) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Add new casting");
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
        List<Role> roles = roleService.getAllRolesByCastingId(id);
        roles.forEach(r -> {
            Long numOfLikes = roleService.countStatusByRole(r.getId(), "liked");
            model.addAttribute(String.format("numOfLikes%s", r.getId()), numOfLikes);
            Long numOfSelftapes = roleService.countStatusByRole(r.getId(), "completed");
            model.addAttribute(String.format("numOfSelftapes%s", r.getId()), numOfSelftapes);
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
                                   @RequestParam int ageTo,
                                   @RequestParam List<MultipartFile> attachments) {
        if(result.hasErrors()) {
            model.addAttribute("title", "Add new role");
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
        for (MultipartFile attachment : attachments) {
            if(!attachment.isEmpty()) {
                roleAttachmentService.storeAttachment(attachment, role);
            }
        }
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
                           @RequestParam int ageTo,
                           @RequestParam List<MultipartFile> attachments) {
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
        for (MultipartFile attachment : attachments) {
            if(!attachment.isEmpty()) {
                roleAttachmentService.storeAttachment(attachment, role);
            }
        }
        Casting casting = castingService.getCastingByRoleId(roleId);
        return String.format("redirect:/director/casting/%s/details", casting.getId());
    }
    @GetMapping("/role/{roleId}/details")
    public String roleDetails(@PathVariable Long roleId, Model model) {
        Casting casting = castingService.getCastingByRoleId(roleId);
        model.addAttribute("castingId", casting.getId());
        Role role = roleService.getRoleById(roleId);
        model.addAttribute("role", role);
        Long numOfLikes = roleService.countStatusByRole(roleId, "liked");
        model.addAttribute("numOfLikes", numOfLikes);
        Long numOfSelftapes = roleService.countStatusByRole(roleId, "completed");
        model.addAttribute("numOfSelftapes", numOfSelftapes);
        FeatureSet featureSet = featureSetService.getFeatureSetByRoleId(roleId);
        model.addAttribute("featureSet", featureSet);
        List<Skill> skills = skillService.getSkillsByRoleId(roleId);
        model.addAttribute("skills", skills);
        List<Actor> actors = actorService.getActorsByRoleStatus(roleId, "liked");
        model.addAttribute("actors", actors);
        List<Selftape> selftapes = selftapeService.getSelftapesByRoleId(roleId);
        model.addAttribute("selftapes", selftapes);
        List<RoleAttachment> attachments = roleAttachmentService.getAttachmentsByRole(role);
        model.addAttribute("attachments", attachments);
        return "role/details";
    }
    @GetMapping("/role/{roleId}/delete")
    public String deleteRole(@PathVariable Long roleId) {
        Casting casting = castingService.getCastingByRoleId(roleId);
        statusService.deleteActorRoleStatusByRoleId(roleId);
        actorRoleService.deleteActorRolesByRoleId(roleId);
        selftapeService.deleteSelftapesByRoleId(roleId);
        roleAttachmentService.deleteAttachmentsByRoleId(roleId);
        FeatureSet featureSet = featureSetService.getFeatureSetByRoleId(roleId);
        roleService.deleteRole(roleId);
        featureSetService.deleteFeatureSet(featureSet);
        return String.format("redirect:/director/casting/%s/details", casting.getId());
    }
    @GetMapping("/casting/list")
    public String castingList(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(user);
        List<Casting> castings = castingService.getActiveCastingsByCastingDirectorId(castingDirector.getId());
        model.addAttribute("castings", castings);
        return "casting/list";
    }
    @GetMapping("/casting/{castingId}/delete")
    public String deleteCasting(@PathVariable Long castingId) {
        List<Role> roles = roleService.getAllRolesByCastingId(castingId);
        List<FeatureSet> featureSetsByCasting = new ArrayList<>();
        for (Role role : roles) {
            statusService.deleteActorRoleStatusByRoleId(role.getId());
            actorRoleService.deleteActorRolesByRoleId(role.getId());
            skillService.deleteRolesSkillsByRoleId(role.getId());
            selftapeService.deleteSelftapesByRoleId(role.getId());
            FeatureSet featureSet = featureSetService.getFeatureSetByRoleId(role.getId());
            featureSetsByCasting.add(featureSet);
            roleAttachmentService.deleteAttachmentsByRoleId(role.getId());
        }
        roleService.deleteRolesByCastingId(castingId);
        for(FeatureSet featureSet : featureSetsByCasting) {
            featureSetService.deleteFeatureSet(featureSet);
        }
        castingService.deleteCasting(castingId);
        return "redirect:/director/casting/list";
    }
    @GetMapping("/casting/{castingId}/edit")
    public String editCastingForm(@PathVariable Long castingId, Model model) {
        model.addAttribute("casting", castingService.getCastingById(castingId));
        model.addAttribute("title", "Edit casting");
        return "casting/form";
    }
    @PostMapping("/casting/{castingId}/edit")
    public String editCasting(@Valid Casting casting,
                              BindingResult result,
                              @PathVariable Long castingId,
                              Model model) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Edit casting");
            return "casting/form";
        }
        Casting castingById = castingService.getCastingById(castingId);
        castingById.setTitle(casting.getTitle());
        castingById.setDescription(casting.getDescription());
        castingById.setDeadline(casting.getDeadline());
        castingById.setActive(castingById.isActive());
        castingById.setCreatedOn(castingById.getCreatedOn());
        castingService.updateCasting(castingById);
        return String.format("redirect:/director/casting/%s/details", castingId);
    }
    @GetMapping("casting/archives")
    public String archivesList(@AuthenticationPrincipal CurrentUser customUser,
                               Model model) {
        User user = customUser.getUser();
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(user);
        List<Casting> castings = castingService.getNonActiveCastingsByCastingDirectorId(castingDirector.getId());
        model.addAttribute("castings", castings);
        return "casting/archives";
    }
    @GetMapping("/casting/archives/{id}/details")
    public String archiveDetails(@PathVariable Long id, Model model) {
        Casting casting = castingService.getCastingById(id);
        List<Role> roles = roleService.getAllRolesByCastingId(id);
        roles.forEach(r -> {
            Long numOfLikes = roleService.countStatusByRole(r.getId(), "liked");
            model.addAttribute(String.format("numOfLikes%s", r.getId()), numOfLikes);
        });
        model.addAttribute("casting", casting);
        model.addAttribute("roles", roles);
        return "casting/archives-details";
    }
    @PostMapping("/selftape/viewed")
    public void markSelftapeAsViewed(@RequestBody Map<String, Long> requestBody) {
        Long actorId = requestBody.get("actorId");
        Long roleId = requestBody.get("roleId");
        ActorRole actorRole = actorRoleService.getActorRoleByActorIdAndRoleId(actorId, roleId);
        Set<Status> statuses = actorRole.getStatuses();
        Status selftapeViewed = statusService.getStatusByName("selftapeViewed");
        statuses.add(selftapeViewed);
        actorRole.setStatuses(statuses);
        actorRoleService.updateActorRole(actorRole);
    }
    @GetMapping("/actors/list")
    public String actorList(Model model,
                               @AuthenticationPrincipal CurrentUser customUser) {
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(customUser.getUser());
        List<Actor> actors = actorService.getActorsByCastingDirectorId(castingDirector.getId());
        List<Photo> photos = new ArrayList<>();
        for(Actor actor : actors) {
            photos.add(photoService.getFirstPhotoByActor(actor));
        }
        model.addAttribute("photos", photos);
        return "actor/list";
    }
    @GetMapping("/actor/{actorId}")
    public String actorProfile(@PathVariable Long actorId, Model model) {
        Actor actor = actorService.getActorById(actorId);
        List<Photo> photos = photoService.getPhotosByActor(actor);
        model.addAttribute("actor", actor);
        model.addAttribute("photos", photos);
        return "actor/profile";
    }
    @GetMapping("/agency/list")
    public String agencyList(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(customUser.getUser());
        List<Agency> agencies = agencyService.getAgenciesByCastingDirectorId(castingDirector.getId());
        model.addAttribute("agencies", agencies);
        return "agency/list";
    }
}
