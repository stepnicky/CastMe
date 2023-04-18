package pl.coderslab.castme.Casting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface CastingRepository extends JpaRepository<Casting, Long> {
    Casting getFirstByOrderByIdDesc();

    @Query(value = "select * from castings where casting_director_id = ?1", nativeQuery = true)
    List<Casting> getAllByCastingDirectorId(Long id);

    @Query( value = "select distinct c.* from castings c join roles r on c.id = r.casting_id " +
            "join actors_roles ar on r.id = ar.role_id join actors a on a.id = ar.actor_id " +
            "where a.id = ?1", nativeQuery = true)
    List<Casting> getByActorId(Long id);
}
