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
        model.addAttribute("castings", castings);
        return "casting-director/dashboard";
    }
}
