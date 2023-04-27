package pl.coderslab.castme.roleattachment;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import pl.coderslab.castme.role.Role;

import javax.persistence.*;

@Entity
@Table(name = "role_attachments")
@Getter
@Setter
public class RoleAttachment {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;
    @ManyToOne
    private Role role;
}
