package pl.coderslab.castme.Actor;

import org.springframework.stereotype.Repository;
import pl.coderslab.castme.Role.Role;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ActorDao {

    private final EntityManager entityManager;

    public ActorDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Actor> getByRoleRequirements(Role role) {
        Query query = entityManager.createQuery("select distinct a from Actor a " +
                "join a.featureSet fs " +
                "where fs.gender = :gender " +
                "and fs.figure like concat('%', :figure, '%') " +
                "and fs.eyeColor like concat('%', :eyeColor, '%') " +
                "and fs.hairColor like concat('%', :hairColor, '%') " +
                "and fs.hairLength like concat('%', :hairLength, '%') " +
                "and fs.height like concat('%', :height, '%') " +
                "and (fs.ageFrom <= :ageFrom " +
                "or fs.ageTo >= :ageTo)");
        query.setParameter("gender", role.getFeatureSet().getGender());
        query.setParameter("figure", role.getFeatureSet().getFigure());
        query.setParameter("eyeColor", role.getFeatureSet().getEyeColor());
        query.setParameter("hairColor", role.getFeatureSet().getHairColor());
        query.setParameter("hairLength", role.getFeatureSet().getHairLength());
        query.setParameter("height", role.getFeatureSet().getHeight());
        query.setParameter("ageFrom", role.getFeatureSet().getAgeFrom());
        query.setParameter("ageTo", role.getFeatureSet().getAgeTo());
        return (List<Actor>) query.getResultList();
    }
}
