package com.example.demo.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public void sendCoordinate(double startLatitude, double startLongitude) throws JsonProcessingException {
        if(startLatitude == -1 && startLatitude==-1){
            Coordinate endSight = new Coordinate(-1, -1);
            String jsonCoordinate = objectMapper.writeValueAsString(endSight);
            rabbitTemplate.convertAndSend("vehicle-coordinates-queue", jsonCoordinate);
            return;
        }
        List<Coordinate> northernCoordinates = new ArrayList<>();
        northernCoordinates.add(new Coordinate(51.5074, -0.1278));
        northernCoordinates.add(new Coordinate(55.7558, 37.6176));
        northernCoordinates.add(new Coordinate(59.3293, 18.0686));
        northernCoordinates.add(new Coordinate(52.5200, 13.4050));
        northernCoordinates.add(new Coordinate(59.9139, 10.7522));

        Random random = new Random();
        int randomIndex = random.nextInt(northernCoordinates.size());
        Coordinate bloodBankCordinates = northernCoordinates.get(randomIndex);

        List<Coordinate> coord =  coordinateGeneratorService.generateCoordinates(startLatitude, startLongitude, bloodBankCordinates.getLatitude(), bloodBankCordinates.getLongitude(), 15);

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