package pl.coderslab.castme.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.castme.User.User;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Actor getByUser(User user);
}
