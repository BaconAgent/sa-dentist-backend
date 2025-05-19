package com.dentists.microservices.appointment_service.consumer;

import com.dentists.microservices.appointment_service.service.AppointmentService;
import com.dentists.dto.ClinicDeletedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Service
public class RabbitMQConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
    private final AppointmentService appointmentService;

    public RabbitMQConsumer(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void handleClinicDeleted(ClinicDeletedEvent event) {
        appointmentService.deletedClinicEvent(event);
    }
}