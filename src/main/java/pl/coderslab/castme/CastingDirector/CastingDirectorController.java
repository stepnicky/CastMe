package pl.coderslab.castme.CastingDirector;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.castme.ActorRole.ActorRole;
import pl.coderslab.castme.ActorRole.ActorRoleService;
import pl.coderslab.castme.Casting.Casting;
import pl.coderslab.castme.Casting.CastingService;
import pl.coderslab.castme.User.CurrentUser;
import pl.coderslab.castme.User.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/director")
public class CastingDirectorController {

    private final CastingService castingService;
    private final CastingDirectorService castingDirectorService;
    private final ActorRoleService actorRoleService;

    public CastingDirectorController(CastingService castingService,
                                     CastingDirectorService castingDirectorService,
                                     ActorRoleService actorRoleService) {
        this.castingService = castingService;
        this.castingDirectorService = castingDirectorService;
        this.actorRoleService = actorRoleService;
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
}
