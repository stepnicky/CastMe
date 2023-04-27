package pl.coderslab.castme.castingdirector;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.user.User;

@Service
public class CastingDirectorService {

    private final CastingDirectorRepository castingDirectorRepository;

    public CastingDirectorService(CastingDirectorRepository castingDirectorRepository) {
        this.castingDirectorRepository = castingDirectorRepository;
    }

    public void addCastingDirector(CastingDirector castingDirector) {
        castingDirectorRepository.save(castingDirector);
    }
    public CastingDirector getCastingDirectorByUser(User user) {
        return castingDirectorRepository.findByUser(user);
    }
    public void updateCastingDirector(CastingDirector castingDirector) {
        castingDirectorRepository.save(castingDirector);
    }
}
