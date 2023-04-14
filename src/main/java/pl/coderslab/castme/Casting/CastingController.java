package pl.coderslab.castme.Casting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/casting")
public class CastingController {

    private final CastingService castingService;

    public CastingController(CastingService castingService) {
        this.castingService = castingService;
    }

    @GetMapping("/add")
    public String addCastingForm(Model model) {
        model.addAttribute("casting", new Casting());
        return "casting/form";
    }

    @PostMapping("/add")
    public String addCasting(@Valid Casting casting, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "casting/form";
        }
        castingService.createCasting(casting);
        return String.format("redirect:/casting/details/%s", casting.getId());
    }

    @GetMapping("/details/{id}")
    public String castingDetails(@PathVariable Long id, Model model) {
        Casting recentCasting = castingService.getCastingById(id);
        model.addAttribute("casting", recentCasting);
        return "casting/details";
    }
}
