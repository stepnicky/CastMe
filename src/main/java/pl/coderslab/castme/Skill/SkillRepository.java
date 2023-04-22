package pl.coderslab.castme.Skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    @Query(value = "select min(id) as id, name from skills group by name", nativeQuery = true)
    List<Skill> getDistinct();

    @Query(value = "select s.* from skills s join roles_skills rs" +
            " on s.id = rs.skills_id where rs.role_id = ?1", nativeQuery = true)
    List<Skill> getByRoleId(Long roleId);

    @Modifying
    @Transactional
    @Query(value = "delete from roles_skills where role_id = ?1", nativeQuery = true)
    void deleteRolesSkillsByRoleId(Long roleId);
}
