package com.dentists.microservices.clinic_service.repository;

import com.dentists.microservices.clinic_service.model.Clinic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClinicRepository extends MongoRepository<Clinic,String> {
}
