package pl.coderslab.castme.RoleAttachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.castme.Role.Role;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleAttachmentRepository extends JpaRepository<RoleAttachment, String> {

    List<RoleAttachment> getByRole(Role role);

    @Modifying
    @Transactional
    @Query(value = "delete from role_attachments where role_id = ?1", nativeQuery = true)
    void deleteByRoleId(Long roleId);
}
