package pl.coderslab.castme.RoleAttachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.castme.Role.Role;

import java.util.List;

public interface RoleAttachmentRepository extends JpaRepository<RoleAttachment, String> {

    List<RoleAttachment> getByRole(Role role);
    @Query(value = "select ra.id from role_attachments ra where ra.role_id = ?1", nativeQuery = true)
    List<String> getIdsByRoleId(Long roleId);
}
