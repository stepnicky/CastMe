package pl.coderslab.castme.actorrole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.castme.actor.Actor;
import pl.coderslab.castme.actorrolestatus.Status;

import javax.transaction.Transactional;
import java.util.List;

public interface ActorRoleRepository extends JpaRepository<ActorRole, Long> {
    List<ActorRole> getAllByActor(Actor actor);
    @Query("select ar from ActorRole ar where ?1 member of ar.statuses")
    List<ActorRole> getAllByStatus(Status status);
    @Modifying
    @Transactional
    @Query(value = "delete from actors_roles where role_id = ?1", nativeQuery = true)
    void deleteByRoleId(Long roleId);
    List<ActorRole> getByRoleId(Long roleId);

    @Query(value = "select * from actors_roles where actor_id = ?1 and role_id=?2", nativeQuery = true)
    ActorRole getByActorIdAndRoleId(Long actorId, Long roleId);

    @Query(value = "select ar.* from actors_roles ar join actors a " +
            "on a.id = ar.actor_id where a.agency_id = ?1", nativeQuery = true)
    List<ActorRole> getAllByAgencyId(Long agencyId);
}
