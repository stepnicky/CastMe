package pl.coderslab.castme.actorrole;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.actor.Actor;
import pl.coderslab.castme.actorrolestatus.Status;
import pl.coderslab.castme.role.Role;

import javax.persistence.*;
import java.util.Set;

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<Status> statuses;
}
