package pl.coderslab.castme.RoleAttachment;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.coderslab.castme.Role.Role;

import java.io.IOException;
import java.util.List;

@Service
public class RoleAttachmentService {

    private final RoleAttachmentRepository roleAttachmentRepository;

    public RoleAttachmentService(RoleAttachmentRepository roleAttachmentRepository) {
        this.roleAttachmentRepository = roleAttachmentRepository;
    }

    public void storeAttachment(MultipartFile attachment, Role role) {
        String fileName = StringUtils.cleanPath(attachment.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException();
            }
            RoleAttachment roleAttachment = new RoleAttachment();
            roleAttachment.setFileName(fileName);
            roleAttachment.setFileType(attachment.getContentType());
            roleAttachment.setData(attachment.getBytes());
            roleAttachment.setRole(role);
            roleAttachmentRepository.save(roleAttachment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public RoleAttachment getAttachmentById(String attachmentId) {
        return roleAttachmentRepository.findById(attachmentId).orElseThrow(RuntimeException::new);
    }
    public List<String> getAttachmentIdsByRoleId(Long roleId) {
        return roleAttachmentRepository.getIdsByRoleId(roleId);
    }
    public void deleteAttachmentById(String attachmentId) {
        roleAttachmentRepository.deleteById(attachmentId);
    }
}
