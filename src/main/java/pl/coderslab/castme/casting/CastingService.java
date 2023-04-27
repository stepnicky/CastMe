package pl.coderslab.castme.casting;

import org.springframework.stereotype.Service;

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

    public List<Casting> getActiveCastingsByCastingDirectorId(Long id) {
        return castingRepository.getActiveByCastingDirectorId(id);
    }

    public List<Casting> getNonActiveCastingsByCastingDirectorId(Long id) {
        return castingRepository.getNonActiveByCastingDirectorId(id);
    }

    public List<Casting> getAllActiveCastings() {
        return castingRepository.getAllActive();
    }

    public List<Casting> getActiveCastingsByActorId(Long id) {
        return castingRepository.getActiveByActorId(id);
    }

    public List<Casting> getNonActiveCastingsByActorId(Long id) {
        return castingRepository.getNonActiveByActorId(id);
    }

    public Long countCastingStatuses(Long castingId, String status) {
        return castingRepository.countStatuses(castingId, status);
    }

    public Casting getCastingByRoleId(Long roleId) {
        return castingRepository.getByRoleId(roleId);
    }

    public void deleteCasting(Long castingId) {
        castingRepository.deleteById(castingId);
    }
}