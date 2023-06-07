package com.example.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VehicleCoordinateService {
    @RabbitListener(queues = "${rabbitmq.queue.vehicleCoordinates}")
    public void receiveCoordinates(String message) {
        // Ovde obradi primljenu poruku sa koordinatama vozila
        System.out.println("Primljena poruka: " + message);
    }
}
