package pl.coderslab.castme.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select * from roles where casting_id = ?1", nativeQuery = true)
    List<Role> getAllByCastingId(Long id);

    @Query(value = "select count(ars.actor_role_id) from actors_roles_statuses ars join statuses s on s.id = ars.status_id " +
            "join actors_roles ar on ar.id = ars.actor_role_id where ar.role_id = ?1 and s.name = ?2", nativeQuery = true)
    Long countStatuses(Long roleId, String status);

    @Modifying
    @Transactional
    @Query(value = "delete from roles where casting_id = ?1", nativeQuery = true)
    void deleteByCastingId(Long castingId);
}
