package pl.coderslab.castme.Selftape;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Role.Role;

import javax.persistence.*;

@Entity
@Table(name = "selftapes")
@Getter
@Setter
@NoArgsConstructor
public class Selftape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Actor actor;

    public Selftape(String link, Role role, Actor actor) {
        this.link = link;
        this.role = role;
        this.actor = actor;
    }
}
