package pl.coderslab.castme.Skill;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.getDistinct();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public void createNewSkill(Skill skill) {
        skillRepository.save(skill);
    }

    public List<Skill> getSkillsByRoleId(Long roleId) {
        return skillRepository.getByRoleId(roleId);
    }
}
