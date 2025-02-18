package com.dentists.microservices.clinic_service;

import org.springframework.boot.SpringApplication;

public class TestClinicServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(ClinicServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
