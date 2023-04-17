package pl.coderslab.castme.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class SkillConverter implements Converter<String, Skill> {

    @Autowired
    private SkillService skillService;

    @Override
    public Skill convert(String source) {
        return skillService.getSkillById(Long.parseLong(source));
    }
}
