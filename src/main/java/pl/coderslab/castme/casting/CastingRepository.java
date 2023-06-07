package pl.coderslab.castme.casting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CastingRepository extends JpaRepository<Casting, Long> {

    @Query(value = "select * from castings where is_active = true", nativeQuery = true)
    List<Casting> getAllActive();

    @Query(value = "select * from castings where casting_director_id = ?1 and is_active = true", nativeQuery = true)
    List<Casting> getActiveByCastingDirectorId(Long id);

    @Query(value = "select * from castings where casting_director_id = ?1 and is_active = false", nativeQuery = true)
    List<Casting> getNonActiveByCastingDirectorId(Long id);

    @Query(value = "select distinct c.* from castings c join roles r on c.id = r.casting_id " +
            "join actors_roles ar on r.id = ar.role_id join actors a on a.id = ar.actor_id " +
            "where a.id = ?1 and c.is_active = true", nativeQuery = true)
    List<Casting> getActiveByActorId(Long id);

    @Query(value = "select distinct c.* from castings c join roles r on c.id = r.casting_id " +
            "join actors_roles ar on r.id = ar.role_id join actors a on a.id = ar.actor_id " +
            "where a.id = ?1 and c.is_active = false", nativeQuery = true)
    List<Casting> getNonActiveByActorId(Long id);

    @Query(value = "select count(ars.actor_role_id) from actors_roles_statuses ars join statuses s on s.id = ars.status_id " +
            "join actors_roles ar on ar.id = ars.actor_role_id join roles r on r.id = ar.role_id" +
            " join castings c on c.id = r.casting_id where c.id = ?1 and s.name = ?2", nativeQuery = true)
    Long countStatuses(Long castingId, String status);

    @Query(value = "select distinct c.* from castings c join roles r on c.id = r.casting_id where r.id = ?1", nativeQuery = true)
    Casting getByRoleId(Long roleId);

    @Query(value = "select distinct c.* from castings c join roles r on c.id = r.casting_id" +
            " join actors_roles ar on r.id = ar.role_id join actors a on a.id = ar.actor_id" +
            " join agencies ag on ag.id = a.agency_id where ag.id = ?1 and c.is_active = true", nativeQuery = true)
    List<Casting> getByAgencyId(Long agencyId);
}
