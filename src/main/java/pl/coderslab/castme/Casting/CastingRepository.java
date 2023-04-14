package pl.coderslab.castme.Casting;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CastingRepository extends JpaRepository<Casting, Long> {
    Casting getFirstByOrderByIdDesc();
}
