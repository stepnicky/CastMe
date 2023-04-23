package pl.coderslab.castme.ActorRoleStatus;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.ActorRole.ActorRole;
import pl.coderslab.castme.ActorRole.ActorRoleRepository;
import pl.coderslab.castme.ActorRole.ActorRoleService;

import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;
    private final ActorRoleRepository actorRoleRepository;

    public StatusService(StatusRepository statusRepository,
                         ActorRoleRepository actorRoleRepository) {
        this.statusRepository = statusRepository;
        this.actorRoleRepository = actorRoleRepository;
    }

    public Status getStatusByName(String name) {
        return statusRepository.getByName(name);
    }
    public void deleteActorRoleStatusByRoleId(Long roleId) {
        List<ActorRole> actorRoleList = actorRoleRepository.getByRoleId(roleId);
        actorRoleList.forEach(ar -> statusRepository.deleteByActorRoleId(ar.getId()));
    }
}
