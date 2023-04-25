package pl.coderslab.castme.Photo;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.castme.Actor.Actor;

import javax.persistence.*;
import java.util.Base64;

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
    @Transient
    private String base64Image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    public String getBase64Image() {
        base64Image = Base64.getEncoder().encodeToString(this.image);
        return base64Image;
    }
}