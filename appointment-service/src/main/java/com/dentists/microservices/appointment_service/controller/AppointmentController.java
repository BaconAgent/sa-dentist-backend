package com.dentists.microservices.appointment_service.controller;

import com.dentists.microservices.appointment_service.dto.AppointmentRequest;
import com.dentists.microservices.appointment_service.model.Appointment;
import com.dentists.microservices.appointment_service.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        return appointmentService.createAppointment(appointmentRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }
    //    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public List <Appointment> getAppointmentsByPatient(@RequestParam Long patientId) {
//        return appointmentService.getAppointmentsByPatientId(patientId);
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment deleted");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
