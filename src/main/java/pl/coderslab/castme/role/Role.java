package pl.coderslab.castme.role;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.featureset.FeatureSet;
import pl.coderslab.castme.selftape.Selftape;
import pl.coderslab.castme.skill.Skill;

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
    @OneToOne(cascade = CascadeType.REMOVE)
    private FeatureSet featureSet;
    @ManyToMany
    private List<Skill> skills;
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private List<Selftape> selftapes;
}
