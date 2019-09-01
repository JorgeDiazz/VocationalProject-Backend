/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

/**
 *
 * @author Jhanuar Sanchez
 */
public class Career {
    
    @Id
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static boolean isCorrectForCreate(Career career) {
        return !StringUtils.isEmpty(career.getName());
    }
}
