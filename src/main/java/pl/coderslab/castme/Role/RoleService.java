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
        List<Actor> actors = actorService.getActorsByRoleRequirements(role);
        roleRepository.save(role);
        actorRoleService.createActorRole(actors, role);
    }

    public Long countStatusByRole(Long roleId, String status) {
        return roleRepository.countStatuses(roleId, status);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void updateRole(Role role) {
        roleRepository.save(role);
        actorRoleService.deleteActorRolesByRoleId(role.getId());
        List<Actor> actors = actorService.getActorsByRoleRequirements(role);
        actorRoleService.createActorRole(actors, role);
    }
}
