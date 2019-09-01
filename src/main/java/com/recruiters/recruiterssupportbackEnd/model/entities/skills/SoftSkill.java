package com.recruiters.recruiterssupportbackEnd.model.entities.skills;

import org.springframework.util.StringUtils;

public class SoftSkill extends Skill {

    public static boolean isCorrectForCreate(SoftSkill softSkill) {
        return !StringUtils.isEmpty(softSkill.getName());
    }
}
