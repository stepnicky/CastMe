package pl.coderslab.castme.CastingDirector;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.castme.User.User;

public interface CastingDirectorRepository extends JpaRepository<CastingDirector, Long> {
    CastingDirector findByUser(User user);
}
