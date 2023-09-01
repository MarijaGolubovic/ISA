package com.example.demo.service;

import com.example.demo.dto.RebbitRequest;
import com.example.demo.websocket.CoordinateSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class VehicleCoordinateService {

    private  final CoordinateSenderService coordinateSenderService;

    public VehicleCoordinateService(CoordinateSenderService coordinateSenderService, RefreshPeriodService refreshPeriodService) {
        this.coordinateSenderService = coordinateSenderService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.vehicleCoordinates}")
    public void receiveCoordinates(String message) throws JsonProcessingException, ExecutionException, InterruptedException {
        System.out.println("Primljena poruka od bolnice: " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        RebbitRequest coordinates = objectMapper.readValue(message, RebbitRequest.class);
        if(Integer.parseInt(coordinates.getStatus()) == 0){
            double latitude = Double.valueOf(coordinates.getLatitude());
            double longitude = Double.valueOf(coordinates.getLongitude());
        coordinateSenderService.sendCoordinate(latitude, longitude, coordinates.getBloodUnits());
        }
        coordinateSenderService.sendCoordinate(-1, -1, 0);
        System.out.println("Slanje zavrseno!");

    }
}
