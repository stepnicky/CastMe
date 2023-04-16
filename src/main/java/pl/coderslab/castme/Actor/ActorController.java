package pl.coderslab.castme.Actor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ActorController {

    @GetMapping("/actor")
    @ResponseBody
    public String actor() {
        return "zalogowano";
    }
}
