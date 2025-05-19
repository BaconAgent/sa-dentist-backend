package com.dentists.dto;

import java.io.Serializable;

public class ClinicDeletedEvent implements Serializable {
    private String clinicId;

    // required for Jackson
    public ClinicDeletedEvent() {}

    public ClinicDeletedEvent(String clinicId) {
        this.clinicId = clinicId;
    }

    public String  getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }
}
