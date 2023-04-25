package pl.coderslab.castme.Photo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.castme.Actor.Actor;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> getByActor(Actor actor);
}
