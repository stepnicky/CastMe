package pl.coderslab.castme.Role;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Actor.ActorService;
import pl.coderslab.castme.ActorRole.ActorRoleService;
import pl.coderslab.castme.ActorRoleStatus.StatusService;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final ActorService actorService;
    private final ActorRoleService actorRoleService;
    private final StatusService statusService;

    public RoleService(RoleRepository roleRepository,
                       ActorService actorService,
                       ActorRoleService actorRoleService,
                       StatusService statusService) {
        this.roleRepository = roleRepository;
        this.actorService = actorService;
        this.actorRoleService = actorRoleService;
        this.statusService = statusService;
    }

    public List<Role> getAllRolesByCastingId(Long id) {
        return roleRepository.getAllByCastingId(id);
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
        statusService.deleteActorRoleStatusByRoleId(role.getId());
        actorRoleService.deleteActorRolesByRoleId(role.getId());
        List<Actor> actors = actorService.getActorsByRoleRequirements(role);
        actorRoleService.createActorRole(actors, role);
    }

    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }

    public void deleteRolesByCastingId(Long castingId) {
        roleRepository.deleteByCastingId(castingId);
    }

}
