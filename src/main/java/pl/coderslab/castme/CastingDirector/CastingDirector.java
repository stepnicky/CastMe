package pl.coderslab.castme.CastingDirector;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Casting.Casting;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name = "casting_directors")
@Getter
@Setter
public class CastingDirector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Email
    private String email;
    @OneToMany
    @JoinColumn(name = "casting_director_id")
    private List<Casting> castings;
}
