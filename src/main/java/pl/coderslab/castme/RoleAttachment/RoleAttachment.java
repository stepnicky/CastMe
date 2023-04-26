package pl.coderslab.castme.RoleAttachment;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import pl.coderslab.castme.Role.Role;

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
