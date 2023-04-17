package pl.coderslab.castme.Skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(value = "SELECT MIN(id) as id, name FROM skills GROUP BY name", nativeQuery = true)
    List<Skill> getDistinct();
}
