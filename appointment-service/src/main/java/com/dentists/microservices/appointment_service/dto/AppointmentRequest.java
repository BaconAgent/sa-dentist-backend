package com.dentists.microservices.appointment_service.dto;

import java.time.Instant;

public record AppointmentRequest(Long patientId, Long doctorId, Long clinicId, String subject, Instant date) {
}
