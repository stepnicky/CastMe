package pl.coderslab.castme.Selftape;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Role.Role;

import javax.persistence.*;

@Entity
@Table(name = "selftapes")
@Getter
@Setter
public class Selftape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Actor actor;
}
