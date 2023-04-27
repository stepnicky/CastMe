package pl.coderslab.castme.agency;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.actor.Actor;
import pl.coderslab.castme.agent.Agent;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "agencies")
@Getter
@Setter
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 2)
    private String name;
    @NotBlank
    private String street;
    @NotBlank
    private String streetNumber;
    @NotBlank
    private String city;
    @OneToMany(mappedBy = "agency")
    private List<Agent> agents;
    @OneToMany(mappedBy = "agency")
    private List<Actor> actors;

}
