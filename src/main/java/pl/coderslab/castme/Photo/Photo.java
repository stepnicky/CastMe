package pl.coderslab.castme.Photo;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Actor.Actor;

import javax.persistence.*;

@Entity
@Table(name = "photos")
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private byte[] image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;

}