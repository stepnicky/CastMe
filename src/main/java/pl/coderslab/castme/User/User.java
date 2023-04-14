package pl.coderslab.castme.User;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.UserRole.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @OneToOne
    private UserRole userRole;
}
