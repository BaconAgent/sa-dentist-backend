package com.dentists.microservices.appointment_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Appointment {
    private Long id;
    private Long patientId;
    private Long doctorId;
    private Long clinicId;
    private String subject;
    private Instant date;
}
