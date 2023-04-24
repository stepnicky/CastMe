package pl.coderslab.castme.FeatureSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FeatureSetRepository extends JpaRepository<FeatureSet, Long> {
    @Query(value = "select fs.* from feature_set fs join roles r on fs.id = r.feature_set_id" +
            " where r.id = ?1", nativeQuery = true)
    FeatureSet getByRoleId(Long roleId);

    @Query(value = "select fs.* from feature_set fs join actors a " +
            "on fs.id = a.feature_set_id where a.id = ?1", nativeQuery = true)
    FeatureSet getByActorId(Long actorId);
}
