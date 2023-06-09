package pl.coderslab.castme.actor;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.castme.actorrole.ActorRole;
import pl.coderslab.castme.actorrole.ActorRoleService;
import pl.coderslab.castme.actorrolestatus.Status;
import pl.coderslab.castme.actorrolestatus.StatusService;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    private final PhotoService photoService;
    private final RoleAttachmentService roleAttachmentService;

    public ActorController(ActorService actorService,
                           SkillService skillService,
                           AgencyService agencyService,
                           FeatureSetService featureSetService,
                           CastingService castingService,
                           ActorRoleService actorRoleService,
                           StatusService statusService,
                           RoleService roleService,
                           SelftapeService selftapeService,
                           PhotoService photoService,
                           RoleAttachmentService roleAttachmentService) {
        this.actorService = actorService;
        this.skillService = skillService;
        this.agencyService = agencyService;
        this.featureSetService = featureSetService;
        this.castingService = castingService;
        this.actorRoleService = actorRoleService;
        this.statusService = statusService;
        this.roleService = roleService;
        this.selftapeService = selftapeService;
        this.photoService = photoService;
        this.roleAttachmentService = roleAttachmentService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        if (actor == null) {
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
    public String profileForm(Model model) {
        model.addAttribute("actor", new Actor());
        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("agencies", agencyService.getAllAgencies());
        model.addAttribute("title", "Complete your actor's profile");
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
                                     @RequestParam("uploadedPhotos") List<MultipartFile> photos,
                                     @AuthenticationPrincipal CurrentUser customUser) throws IOException {
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
        for(MultipartFile photo : photos) {
            if(!photo.isEmpty()) {
                Photo p = new Photo();
                p.setImage(photo.getBytes());
                p.setActor(actor);
                photoService.savePhoto(p);
            }
        }
        return "redirect:/actor";
    }

    @GetMapping("/my-profile")
    public String myProfile(@AuthenticationPrincipal CurrentUser customUser,
                          Model model) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        model.addAttribute("actor", actor);
        List<Photo> photos = photoService.getPhotosByActor(actor);
        model.addAttribute("photos", photos);
        return "actor/profile";
    }

    @GetMapping("/my-profile/edit")
    public String editMyProfileForm(@AuthenticationPrincipal CurrentUser customUser,
                                Model model) {
        Actor actor = actorService.getActorByUser(customUser.getUser());
        model.addAttribute("actor", actor);
        FeatureSet featureSet = featureSetService.getFeatureSetByActorId(actor.getId());
        model.addAttribute("featureSet", featureSet);
        model.addAttribute("skills", skillService.getAllSkills());
        model.addAttribute("agencies", agencyService.getAllAgencies());
        model.addAttribute("title", "Edit your actors profile");
        return "actor/form";
    }

    @PostMapping("/my-profile/edit")
    public String editMyProfile(Actor actor,
                                @AuthenticationPrincipal CurrentUser customUser,
                                @RequestParam String gender,
                                @RequestParam String height,
                                @RequestParam String hairColor,
                                @RequestParam String hairLength,
                                @RequestParam String eyeColor,
                                @RequestParam String figure,
                                @RequestParam int ageFrom,
                                @RequestParam int ageTo,
                                @RequestParam("uploadedPhotos") List<MultipartFile> photos,
                                @RequestParam String newSkill) throws IOException {
        Actor actorByUser = actorService.getActorByUser(customUser.getUser());
        FeatureSet featureSet = featureSetService.getFeatureSetByActorId(actorByUser.getId());
        featureSet.setGender(gender);
        featureSet.setHeight(height);
        featureSet.setHairColor(hairColor);
        featureSet.setHairLength(hairLength);
        featureSet.setEyeColor(eyeColor);
        featureSet.setFigure(figure);
        featureSet.setAgeFrom(ageFrom);
        featureSet.setAgeTo(ageTo);
        featureSetService.updateFeatureSet(featureSet);
        actorByUser.setFeatureSet(featureSet);
        if (!newSkill.isBlank()) {
            Skill skill = new Skill();
            skill.setName(newSkill);
            skillService.createNewSkill(skill);
            List<Skill> skills = actor.getSkills();
            skills.add(skill);
            actorByUser.setSkills(skills);
        }
        actorByUser.setAgency(actor.getAgency());
        actorByUser.setEducation(actor.getEducation());
        for(MultipartFile photo : photos) {
            if(!photo.isEmpty()) {
                Photo p = new Photo();
                p.setImage(photo.getBytes());
                p.setActor(actorByUser);
                photoService.savePhoto(p);
            }
        }
        actorService.updateActor(actorByUser);
        return "redirect:/actor/my-profile";
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
            Long numOfSelftapes = roleService.countStatusByRole(r.getId(), "completed");
            model.addAttribute(String.format("numOfSelftapes%s", r.getId()), numOfSelftapes);
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

    @PostMapping("selftape/add")
    public String uploadSelftape(@AuthenticationPrincipal CurrentUser customUser,
                                 @RequestParam String link,
                                 @RequestParam Long roleId) {
        User user = customUser.getUser();
        Actor actor = actorService.getActorByUser(user);
        Role role = roleService.getRoleById(roleId);
        Selftape selftape = new Selftape(link, role, actor);
        selftapeService.saveSelftape(selftape);
        ActorRole actorRole = actorRoleService.getActorRoleByActorIdAndRoleId(actor.getId(), roleId);
        Status completed = statusService.getStatusByName("completed");
        Status selftapeViewed = statusService.getStatusByName("selftapeViewed");
        Set<Status> statuses = actorRole.getStatuses();
        statuses.remove(selftapeViewed);
        statuses.add(completed);
        actorRole.setStatuses(statuses);
        actorRoleService.updateActorRole(actorRole);
        return String.format("redirect:/actor/role/%s/details", roleId);
    }
}
