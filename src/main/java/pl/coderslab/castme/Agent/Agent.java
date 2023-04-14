package pl.coderslab.castme.Agent;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Agency.Agency;
import pl.coderslab.castme.User.User;
import pl.coderslab.castme.UserRole.UserRole;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "agents")
@Getter
@Setter
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
