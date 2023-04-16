package pl.coderslab.castme.ActorRole;

import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.Role.Role;

import javax.persistence.*;

@Entity
@Table(name = "actors_roles")
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
