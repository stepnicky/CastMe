package pl.coderslab.castme.Casting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CastingRepository extends JpaRepository<Casting, Long> {
    Casting getFirstByOrderByIdDesc();
    @Query(value = "select * from castings where casting_director_id = ?1", nativeQuery = true)
    List<Casting> getAllByCastingDirectorId(Long id);
}
