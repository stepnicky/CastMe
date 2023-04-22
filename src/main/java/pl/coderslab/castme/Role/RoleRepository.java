package pl.coderslab.castme.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select * from roles where casting_id = ?1", nativeQuery = true)
    List<Role> getAllByCastingId(Long id);

    @Query(value = "select count(ar.status) from actors_roles ar " +
            "join roles r on ar.role_id = r.id where r.id = ?1 and ar.status = ?2", nativeQuery = true)
    Long countStatuses(Long roleId, String status);

    @Modifying
    @Transactional
    @Query(value = "delete from roles where casting_id = ?1", nativeQuery = true)
    void deleteByCastingId(Long castingId);
}
