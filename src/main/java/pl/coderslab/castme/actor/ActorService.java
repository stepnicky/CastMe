package pl.coderslab.castme.actor;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.role.Role;
import pl.coderslab.castme.user.User;

import java.util.HashSet;
import java.util.List;

@Service
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorDao actorDao;

    public ActorService(ActorRepository actorRepository, ActorDao actorDao) {
        this.actorRepository = actorRepository;
        this.actorDao = actorDao;
    }

    public Actor getActorByUser(User user) {
        return actorRepository.getByUser(user);
    }

    public void createActor(Actor actor) {
        actorRepository.save(actor);
    }

    public List<Actor> getActorsByRoleRequirements(Role role) {
        List<Actor> actors = actorDao.getByRoleRequirements(role);
        actors.removeIf(a -> !new HashSet<>(a.getSkills()).containsAll(role.getSkills()));
        return actors;
    }

    public List<Actor> getActorsByRoleStatus(Long roleId, String status) {
        return actorRepository.getByRoleStatus(roleId, status);
    }

    public List<Actor> getActorsByCastingDirectorId(Long id) {
        return actorRepository.getByCastingDirectorId(id);
    }

    public void updateActor(Actor actor) {
        actorRepository.save(actor);
    }

    public Actor getActorById(Long actorId) {
        return actorRepository.findById(actorId).orElseThrow(RuntimeException::new);
    }

    public List<Actor> getActorsByAgencyIdAndRoleId(Long agencyId, Long roleId) {
        return actorRepository.getByAgencyIdAndRoleId(agencyId, roleId);
    }
}
