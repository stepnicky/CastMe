package pl.coderslab.castme.Casting;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Role.Role;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "castings")
@Getter
@Setter
public class Casting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private LocalDateTime deadline;
    @OneToMany
    @JoinColumn(name = "casting_id")
    private List<Role> roles;
    private boolean isActive;
}
