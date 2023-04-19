package pl.coderslab.castme.CastingDirector;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.castme.Casting.Casting;
import pl.coderslab.castme.Casting.CastingService;
import pl.coderslab.castme.User.CurrentUser;
import pl.coderslab.castme.User.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
@RequestMapping("/director")
public class CastingDirectorController {

    private final CastingService castingService;
    private final CastingDirectorService castingDirectorService;

    public CastingDirectorController(CastingService castingService,
                                     CastingDirectorService castingDirectorService) {
        this.castingService = castingService;
        this.castingDirectorService = castingDirectorService;
    }

    @GetMapping("")
    public String dashboard(@AuthenticationPrincipal CurrentUser customUser, Model model) {
        User user = customUser.getUser();
        CastingDirector castingDirector = castingDirectorService.getCastingDirectorByUser(user);
        List<Casting> castings = castingService.getCastingsByCastingDirector(castingDirector.getId());
        castings.forEach(c -> {
            Long numOfLikes = castingService.countCastingStatuses(castingDirector.getId(), c.getId(), "accepted");
            model.addAttribute(String.format("numOfLikes%s", c.getId()), numOfLikes);
            Long numOfSelftapes = castingService.countCastingStatuses(castingDirector.getId(), c.getId(), "completed");
            model.addAttribute(String.format("numOfSelftapes%s", c.getId()), numOfSelftapes);
            LocalDate now = LocalDate.now();
            LocalDate deadline = c.getDeadline();
            Integer daysTillDeadline = Period.between(now, deadline).getDays();
            model.addAttribute(String.format("daysTillDeadline%s", c.getId()), daysTillDeadline);
        });
        model.addAttribute("castings", castings);
        return "casting-director/dashboard";
    }
}
