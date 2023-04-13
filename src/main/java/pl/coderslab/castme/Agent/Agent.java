package pl.coderslab.castme.Agent;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Agency.Agency;

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
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String email;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
