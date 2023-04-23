package pl.coderslab.castme.ActorRoleStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status getByName(String name);
    @Modifying
    @Transactional
    @Query(value = "delete from actors_roles_statuses where actor_role_id = ?1", nativeQuery = true)
    void deleteByActorRoleId(Long actorRoleId);
}
