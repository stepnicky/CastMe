package pl.coderslab.castme.actor;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.actorrole.ActorRole;
import pl.coderslab.castme.agency.Agency;
import pl.coderslab.castme.featureset.FeatureSet;
import pl.coderslab.castme.photo.Photo;
import pl.coderslab.castme.selftape.Selftape;
import pl.coderslab.castme.skill.Skill;
import pl.coderslab.castme.user.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "actors")
@Getter
@Setter
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private String education;
    @ManyToOne
    private FeatureSet featureSet;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Skill> skills;
    @OneToMany(mappedBy = "actor")
    private List<ActorRole> actorRoles;

    @OneToMany(mappedBy = "actor")
    private List<Photo> photos;
    @OneToMany(mappedBy = "actor")
    private List<Selftape> selftapes;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
