package pl.coderslab.castme.ActorRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.castme.Actor.Actor;

import javax.transaction.Transactional;
import java.util.List;

public interface ActorRoleRepository extends JpaRepository<ActorRole, Long> {
    List<ActorRole> getAllByActor(Actor actor);
    List<ActorRole> getAllByStatus(String status);
    @Modifying
    @Transactional
    @Query(value = "delete from actors_roles where role_id = ?1", nativeQuery = true)
    void deleteByRoleId(Long roleId);

    @Query(value = "select * from actors_roles where actor_id = ?1 and role_id=?2", nativeQuery = true)
    ActorRole getByActorIdAndRoleId(Long actorId, Long roleId);
}
