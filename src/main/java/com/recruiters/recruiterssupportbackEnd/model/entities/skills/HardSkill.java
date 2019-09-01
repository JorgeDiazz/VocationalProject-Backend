package com.recruiters.recruiterssupportbackEnd.model.entities.skills;

import org.springframework.util.StringUtils;

public class HardSkill extends Skill {

    public static boolean isCorrectForCreate(HardSkill hardSkill) {
        return !StringUtils.isEmpty(hardSkill.getName());
    }
}
