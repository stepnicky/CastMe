package pl.coderslab.castme.agency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgencyRepository extends JpaRepository<Agency, Long> {

    @Query(value = "select distinct a.* from agencies a join actors a2 on a.id = a2.agency_id" +
            " join actors_roles ar on a2.id = ar.actor_id join roles r on r.id = ar.role_id" +
            " join castings c on c.id = r.casting_id where c.casting_director_id = ?1", nativeQuery = true)
    List<Agency> getByCastingDirectorId(Long id);
}
