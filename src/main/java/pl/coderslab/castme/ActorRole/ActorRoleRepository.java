package pl.coderslab.castme.ActorRole;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.castme.Actor.Actor;
import java.util.List;

public interface ActorRoleRepository extends JpaRepository<ActorRole, Long> {
    List<ActorRole> getAllByActor(Actor actor);
    List<ActorRole> getAllByStatus(String status);
}
