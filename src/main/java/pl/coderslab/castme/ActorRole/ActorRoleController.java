package pl.coderslab.castme.ActorRole;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.castme.ActorRoleStatus.Status;
import pl.coderslab.castme.ActorRoleStatus.StatusService;
import pl.coderslab.castme.User.CurrentUser;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/actor-role")
public class ActorRoleController {

    private final ActorRoleService actorRoleService;
    private final StatusService statusService;

    public ActorRoleController(ActorRoleService actorRoleService,
                               StatusService statusService) {
        this.actorRoleService = actorRoleService;
        this.statusService = statusService;
    }

    @PostMapping("/viewed")
    public void markAsViewed(@RequestBody Map<String, List<Long>> actorRolesIdMap,
                                  @AuthenticationPrincipal CurrentUser customUser) {
        List<Long> actorRolesIds = actorRolesIdMap.get("numbers");
        actorRolesIds.forEach(id -> {
            ActorRole actorRole = actorRoleService.getActorRoleById(id);
            String userRole = customUser.getUser().getUserRole().getUserRole();
            Set<Status> statuses = actorRole.getStatuses();
            if (userRole.equals("ROLE_ACTOR")) {
                Status status = statusService.getStatusByName("viewedByActor");
                statuses.add(status);
            } else if (userRole.equals("ROLE_CASTING_DIRECTOR")) {
                Status status = statusService.getStatusByName("likeViewedByCastingDirector");
                statuses.add(status);
            }
            actorRole.setStatuses(statuses);
            actorRoleService.updateActorRole(actorRole);
        });
    }
    @PostMapping("/status/add")
    public void addRoleStatus(@RequestBody Map<String, String> actorRoleData) {
        Long id = Long.parseLong(actorRoleData.get("actorRoleId"));
        String statusName = actorRoleData.get("status");
        ActorRole actorRole = actorRoleService.getActorRoleById(id);
        Status status = statusService.getStatusByName(statusName);
        Set<Status> statuses = actorRole.getStatuses();
        statuses.add(status);
        actorRole.setStatuses(statuses);
        actorRoleService.updateActorRole(actorRole);
    }
    @PostMapping("/status/delete")
    public void deleteRoleStatus(@RequestBody Map<String, String> actorRoleData) {
        Long id = Long.parseLong(actorRoleData.get("actorRoleId"));
        String statusName = actorRoleData.get("status");
        ActorRole actorRole = actorRoleService.getActorRoleById(id);
        Set<Status> statuses = actorRole.getStatuses();
        statuses.removeIf(s -> s.getName().equals(statusName));
        actorRole.setStatuses(statuses);
        actorRoleService.updateActorRole(actorRole);
    }
}
