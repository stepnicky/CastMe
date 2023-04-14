package pl.coderslab.castme.CastingDirector;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Casting.Casting;
import pl.coderslab.castme.User.User;

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
    @OneToOne
    private User user;
    @OneToMany
    @JoinColumn(name = "casting_director_id")
    private List<Casting> castings;
}
