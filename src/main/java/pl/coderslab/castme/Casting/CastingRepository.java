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
            "where a.id = ?1 and c.is_active = true", nativeQuery = true)
    List<Casting> getActiveByActorId(Long id);

    @Query(value = "select count(ar.status) as number_of_likes from actors_roles ar join roles r on r.id = ar.role_id\n" +
            "join castings c on c.id = r.casting_id join casting_directors cd on cd.id = c.casting_director_id\n" +
            "where casting_director_id = ?1 and c.id = ?2 and ar.status = ?3", nativeQuery = true)
    Long countStatuses(Long castingDirectorId, Long castingId, String status);
}
