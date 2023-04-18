package pl.coderslab.castme.Casting;

import org.springframework.stereotype.Service;
import pl.coderslab.castme.Actor.Actor;
import pl.coderslab.castme.ActorRole.ActorRole;

import java.util.List;

@Service
public class CastingService {

    private final CastingRepository castingRepository;


    public CastingService(CastingRepository castingRepository) {
        this.castingRepository = castingRepository;
    }

    public void createCasting(Casting casting) {
        castingRepository.save(casting);
    }
    public Casting getCastingById(Long id) {
        return castingRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    public void updateCasting(Casting casting) {
        castingRepository.save(casting);
    }
    public List<Casting> getCastingsByCastingDirector(Long id) {
        return castingRepository.getAllByCastingDirectorId(id);
    }
    public List<Casting> getCastingsByActorId(Long id) {
        return castingRepository.getByActorId(id);
    }
}
