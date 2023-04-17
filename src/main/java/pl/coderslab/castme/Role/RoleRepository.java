package pl.coderslab.castme.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select * from roles where casting_id = ?1", nativeQuery = true)
    List<Role> getAllByCasting(Long id);
}
