package pl.coderslab.castme.agent;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.agency.Agency;
import pl.coderslab.castme.user.User;

import javax.persistence.*;

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
