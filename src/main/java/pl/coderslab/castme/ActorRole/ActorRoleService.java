package pl.coderslab.castme.ActorRole;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.ActorRoleStatus.Status;
import pl.coderslab.castme.ActorRoleStatus.StatusService;
import pl.coderslab.castme.Role.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ActorRoleService {

    private final ActorRoleRepository actorRoleRepository;
    private final StatusService statusService;

    public ActorRoleService(ActorRoleRepository actorRoleRepository,
                            StatusService statusService) {
        this.actorRoleRepository = actorRoleRepository;
        this.statusService = statusService;
    }

    public List<ActorRole> getAllActorRolesByActor(Actor actor) {
        return actorRoleRepository.getAllByActor(actor);
    }

    public void createActorRole(List<Actor> actors, Role role) {
        actors.forEach(a -> {
            ActorRole actorRole = new ActorRole();
            actorRole.setActor(a);
            actorRole.setRole(role);
            Set<Status> statuses = new HashSet<>();
            Status status = statusService.getStatusByName("invited");
            statuses.add(status);
            actorRole.setStatuses(statuses);
            actorRoleRepository.save(actorRole);
        });
    }

    public void updateActorRole(ActorRole actorRole) {
        actorRoleRepository.save(actorRole);
    }

    public ActorRole getActorRoleById(Long id) {
        return actorRoleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<ActorRole> getActorRolesByStatus(Status status) {
        return actorRoleRepository.getAllByStatus(status);
    }

    public List<ActorRole> getActorRolesByRoleId(Long roleId) {
        return actorRoleRepository.getByRoleId(roleId);
    }

    public ActorRole getActorRoleByActorIdAndRoleId(Long actorId, Long roleId) {
        return actorRoleRepository.getByActorIdAndRoleId(actorId, roleId);
    }

    public void deleteActorRolesByRoleId(Long roleId) {
        actorRoleRepository.deleteByRoleId(roleId);
    }
}
