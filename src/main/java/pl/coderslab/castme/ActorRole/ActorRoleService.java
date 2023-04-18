package pl.coderslab.castme.ActorRole;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Role.Role;

import java.util.List;

@Service
public class ActorRoleService {

    private final ActorRoleRepository actorRoleRepository;

    public ActorRoleService(ActorRoleRepository actorRoleRepository) {
        this.actorRoleRepository = actorRoleRepository;
    }

    public List<ActorRole> getAllActorRolesByActor(Actor actor) {
        return actorRoleRepository.getAllByActor(actor);
    }

    public void createActorRole(List<Actor> actors, Role role) {
        actors.forEach(a -> {
            ActorRole actorRole = new ActorRole();
            actorRole.setActor(a);
            actorRole.setRole(role);
            actorRole.setStatus("invited");
            actorRoleRepository.save(actorRole);
        });
    }

}
