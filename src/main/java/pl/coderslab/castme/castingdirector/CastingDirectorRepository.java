package pl.coderslab.castme.castingdirector;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.castme.user.User;

public interface CastingDirectorRepository extends JpaRepository<CastingDirector, Long> {
    CastingDirector findByUser(User user);
}
