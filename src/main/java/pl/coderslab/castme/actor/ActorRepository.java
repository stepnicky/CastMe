package pl.coderslab.castme.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.castme.user.User;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor getByUser(User user);

    @Query(value = "select a.* from actors a join actors_roles ar on a.id = ar.actor_id" +
            " join actors_roles_statuses ars on ar.id = ars.actor_role_id " +
            "join statuses s on s.id = ars.status_id " +
            "where ar.role_id = ?1 and s.name = ?2", nativeQuery = true)
    List<Actor> getByRoleStatus(Long roleId, String status);

    @Query(value = "select distinct a.* from actors a join actors_roles ar on a.id = ar.actor_id " +
            "join roles r on r.id = ar.role_id " +
            "join castings c on c.id = r.casting_id where c.casting_director_id = ?1", nativeQuery = true)
    List<Actor> getByCastingDirectorId(Long id);
}
