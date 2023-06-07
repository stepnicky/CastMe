package pl.coderslab.castme.user;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.userrole.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    @OneToOne
    private UserRole userRole;
}
