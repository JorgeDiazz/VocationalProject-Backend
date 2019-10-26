
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

/**
 *
 * @author seam33
 */

public class ApplyVacant {
    
    private String idPostulant;
    private int idVacant;

    public String getIdPostulant() {
        return idPostulant;
    }

    public void setIdPostulant(String idPostulant) {
        this.idPostulant = idPostulant;
    }

    public int getIdVacant() {
        return idVacant;
    }

    public void setIdVacant(int idVacant) {
        this.idVacant = idVacant;
    }
    
}
