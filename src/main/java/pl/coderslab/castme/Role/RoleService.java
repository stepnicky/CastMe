package pl.coderslab.castme.Role;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Actor.ActorService;
import pl.coderslab.castme.ActorRole.ActorRoleService;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final ActorService actorService;
    private final ActorRoleService actorRoleService;

    public RoleService(RoleRepository roleRepository,
                       ActorService actorService,
                       ActorRoleService actorRoleService) {
        this.roleRepository = roleRepository;
        this.actorService = actorService;
        this.actorRoleService = actorRoleService;
    }

    public List<Role> getAllRolesByCasting(Long id) {
        return roleRepository.getAllByCasting(id);
    }

    public void addNewRole(Role role) {
        List<Actor> actors = actorService.getActorByRoleRequirements(role);
        roleRepository.save(role);
        actorRoleService.createActorRole(actors, role);
    }
}
