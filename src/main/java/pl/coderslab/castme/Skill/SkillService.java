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
}
