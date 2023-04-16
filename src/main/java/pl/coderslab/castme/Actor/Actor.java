package pl.coderslab.castme.Actor;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.ActorRole.ActorRole;
import pl.coderslab.castme.Agency.Agency;
import pl.coderslab.castme.FeatureSet.FeatureSet;
import pl.coderslab.castme.Photo.Photo;
import pl.coderslab.castme.Role.Role;
import pl.coderslab.castme.Skill.Skill;
import pl.coderslab.castme.User.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
    @ManyToMany
    private List<Skill> skills;
    @OneToMany(mappedBy = "actor")
    private List<ActorRole> actorRoles;

    @OneToMany(mappedBy = "actor")
    private List<Photo> photos;
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;
}
