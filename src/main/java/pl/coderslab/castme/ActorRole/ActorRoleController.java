package pl.coderslab.castme.ActorRole;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.castme.User.CurrentUser;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/actor-role")
public class ActorRoleController {

    private final ActorRoleService actorRoleService;

    public ActorRoleController(ActorRoleService actorRoleService) {
        this.actorRoleService = actorRoleService;
    }

    @PostMapping("/viewed")
    public void markAsViewed(@RequestBody Map<String, List<Long>> actorRolesIdMap,
                                  @AuthenticationPrincipal CurrentUser customUser) {
        List<Long> actorRolesIds = actorRolesIdMap.get("numbers");
        actorRolesIds.forEach(id -> {
            ActorRole actorRole = actorRoleService.getActorRoleById(id);
            String userRole = customUser.getUser().getUserRole().getUserRole();
            if(userRole.equals("ROLE_ACTOR")) {
                actorRole.setStatus("viewedByActor");
            } else if (userRole.equals("ROLE_CASTING_DIRECTOR")) {
                actorRole.setStatus("viewedByCastingDirector");
            }
            actorRoleService.updateActorRole(actorRole);
        });
    }
    @PostMapping("/status/change")
    public void changeRoleStatus(@RequestBody Map<String, String> actorRoleData) {
        Long id = Long.parseLong(actorRoleData.get("actorRoleId"));
        String action = actorRoleData.get("action");
        ActorRole actorRole = actorRoleService.getActorRoleById(id);
        actorRole.setStatus(action);
        actorRoleService.updateActorRole(actorRole);
    }
}
