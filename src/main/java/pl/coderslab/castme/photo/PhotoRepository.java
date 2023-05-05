package pl.coderslab.castme.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.castme.actor.Actor;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> getByActor(Actor actor);
    Photo getTopByActor(Actor actor);
}
