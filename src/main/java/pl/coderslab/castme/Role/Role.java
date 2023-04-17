package pl.coderslab.castme.Role;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.FeatureSet.FeatureSet;
import pl.coderslab.castme.Skill.Skill;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToOne
    private FeatureSet featureSet;
    @ManyToMany
    private List<Skill> skills;

}
