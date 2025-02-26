package com.dentists.microservices.appointment_service.service;

import com.dentists.microservices.appointment_service.dto.AppointmentRequest;
import com.dentists.microservices.appointment_service.entity.AppointmentEntity;
import com.dentists.microservices.appointment_service.model.Appointment;
import com.dentists.microservices.appointment_service.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public Appointment createAppointment(AppointmentRequest appointmentRequest){
        Appointment appointment = Appointment.builder()
                .patientId(appointmentRequest.patientId())
                .doctorId(appointmentRequest.doctorId())
                .clinicId(appointmentRequest.clinicId())
                .subject(appointmentRequest.subject())
                .date(appointmentRequest.date())
                .build();
        AppointmentEntity appointmentEntity = new AppointmentEntity(appointment);
        appointmentRepository.save(appointmentEntity);
        log.info("Appointment created");
        return new Appointment(appointment.getId(),appointment.getPatientId(),appointmentRequest.doctorId(),appointment.getClinicId(),appointment.getSubject(),appointment.getDate());
    }
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll()
                .stream().map(appointment -> new Appointment(appointment.getId(), appointment.getPatientId(), appointment.getDoctorId(), appointment.getClinicId(), appointment.getSubject(),appointment.getDate()))
                .toList();
    }
    public List<Appointment> getAppointmentsPerPatientId(Long id){
        return appointmentRepository.findAppointmentsByPatientId(id)
                .stream().map(appointment -> new Appointment(appointment.getId(), appointment.getPatientId(), appointment.getDoctorId(), appointment.getClinicId(), appointment.getSubject(), appointment.getDate()))
                .toList();
    }
    public List<Appointment> getAppointmentsPerDate(Instant date){
        return appointmentRepository.findAppointmentsByDate(date)
                .stream().map(appointment -> new Appointment(appointment.getId(), appointment.getPatientId(), appointment.getDoctorId(), appointment.getClinicId(), appointment.getSubject(), appointment.getDate()))
                .toList();
    }
    public void updateAppointment(AppointmentRequest appointmentRequest){
        //asda
    }
    public void deleteAppointment(Long id){
        if(appointmentRepository.existsById(id)){
            appointmentRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("Appointment not found");
        }
    }
}
