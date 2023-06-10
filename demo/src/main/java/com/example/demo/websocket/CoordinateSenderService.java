package com.example.demo.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CoordinateSenderService {

    private final RabbitTemplate rabbitTemplate;
    private final CoordinateGeneratorService coordinateGeneratorService;
    private final ObjectMapper objectMapper; // Dodato


    @Autowired
    public CoordinateSenderService(RabbitTemplate rabbitTemplate, CoordinateGeneratorService coordinateGeneratorService, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.coordinateGeneratorService = coordinateGeneratorService;
        this.objectMapper = objectMapper;
    }

    public void sendCoordinate(Coordinate coordinate) throws JsonProcessingException {
        List<Coordinate> coord =  coordinateGeneratorService.generateCoordinates(12, 25, 45, 34, 10);
        for (Coordinate c: coord){
            String jsonCoordinate = objectMapper.writeValueAsString(c);
            rabbitTemplate.convertAndSend("vehicle-coordinates-queue", jsonCoordinate);
            sleep(1);
        }
        System.out.println("Kretanje vozila zavrseno!");
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}