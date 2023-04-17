package pl.coderslab.castme.ActorRole;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Role.Role;

import javax.persistence.*;

@Entity
@Table(name = "actors_roles")
@Getter
@Setter
public class ActorRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Actor actor;
    @ManyToOne
    private Role role;
    private String status;
}
