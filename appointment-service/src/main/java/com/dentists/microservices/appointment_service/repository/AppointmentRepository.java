package com.dentists.microservices.appointment_service.repository;

import com.dentists.microservices.appointment_service.entity.AppointmentEntity;
import com.dentists.microservices.appointment_service.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<Appointment> findAppointmentsByPatientId(Long patientId);

    List<Appointment> findAppointmentsByDate(Instant date);
}
