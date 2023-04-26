package pl.coderslab.castme.RoleAttachment;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attachment")
public class RoleAttachmentController {

    private final RoleAttachmentService roleAttachmentService;

    public RoleAttachmentController(RoleAttachmentService roleAttachmentService) {
        this.roleAttachmentService = roleAttachmentService;
    }

    @GetMapping("/{attachmentId}/download")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable String attachmentId) {
        RoleAttachment attachment = roleAttachmentService.getAttachmentById(attachmentId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

    @DeleteMapping("/{attachmentId}/delete")
    public void deleteAttachment(@PathVariable String attachmentId) {
        RoleAttachment attachment = roleAttachmentService.getAttachmentById(attachmentId);
        roleAttachmentService.deleteAttachment(attachment);
    }
}
