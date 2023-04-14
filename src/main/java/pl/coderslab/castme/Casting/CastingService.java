package pl.coderslab.castme.Casting;

import org.springframework.stereotype.Service;

@Service
public class CastingService {

    private final CastingRepository castingRepository;


    public CastingService(CastingRepository castingRepository) {
        this.castingRepository = castingRepository;
    }

    public void createCasting(Casting casting) {
        castingRepository.save(casting);
    }
    public Casting getRecentCasting() {
        return castingRepository.getFirstByOrderByIdDesc();
    }

    public Casting getCastingById(Long id) {
        return castingRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
