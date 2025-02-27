package com.dentists.microservices.clinic_service.controller;

import com.dentists.microservices.clinic_service.dto.ClinicRequest;
import com.dentists.microservices.clinic_service.dto.ClinicResponse;
import com.dentists.microservices.clinic_service.publisher.RabbitMQProducer;
import com.dentists.microservices.clinic_service.service.ClinicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinic")
@RequiredArgsConstructor
public class ClinicController {
    private final ClinicService clinicService;
    private final RabbitMQProducer rabbitMQProducer;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClinicResponse createClinic(@RequestBody ClinicRequest clinicRequest){
        return clinicService.createClinic(clinicRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClinicResponse> getAllClinics(){
        return clinicService.getAllClinics();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClinic(@PathVariable String id){
        try {
            clinicService.deleteClinicById(id);
            return ResponseEntity.ok("Clinic deleted successfully.");
        }
        catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClinicResponse updateClinic(@RequestBody ClinicRequest clinicRequest){
        return clinicService.updateClinic(clinicRequest);
    }
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ.");
    }
}
