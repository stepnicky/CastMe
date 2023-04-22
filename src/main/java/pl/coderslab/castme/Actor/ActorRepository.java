package pl.coderslab.castme.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.castme.User.User;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor getByUser(User user);

    @Query(value = "select a.* from actors a join actors_roles ar on a.id = ar.actor_id " +
            "where ar.role_id = ?1 and ar.status = ?2", nativeQuery = true)
    List<Actor> getByRoleStatus(Long roleId, String status);
}
