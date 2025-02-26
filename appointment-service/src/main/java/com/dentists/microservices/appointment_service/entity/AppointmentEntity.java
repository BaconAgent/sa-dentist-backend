package com.dentists.microservices.appointment_service.entity;


import com.dentists.microservices.appointment_service.model.Appointment;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name="appointments")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Column(name = "clinic_id", nullable = false)
    private Long clinicId;

    @Column(name = "subject", nullable = false, length = 255)
    private String subject;

    @Column(name = "date", nullable = false)
    private Instant date;

    public AppointmentEntity() {
    }

    public AppointmentEntity(Appointment appointment) {
        this.id = appointment.getId();
        this.patientId = appointment.getPatientId();
        this.doctorId = appointment.getDoctorId();
        this.clinicId = appointment.getClinicId();
        this.subject = appointment.getSubject();
        this.date = appointment.getDate();
    }
}
