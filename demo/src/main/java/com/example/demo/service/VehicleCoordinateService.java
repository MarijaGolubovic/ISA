package com.example.demo.service;

import com.example.demo.dto.RebbitRequest;
import com.example.demo.websocket.Coordinate;
import com.example.demo.websocket.CoordinateSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VehicleCoordinateService {

    private  final CoordinateSenderService coordinateSenderService;

    public VehicleCoordinateService(CoordinateSenderService coordinateSenderService) {
        this.coordinateSenderService = coordinateSenderService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.vehicleCoordinates}")
    public void receiveCoordinates(String message) throws JsonProcessingException {
        System.out.println("Primljena poruka od bolnice: " + message);

        ObjectMapper objectMapper = new ObjectMapper();
        RebbitRequest coordinates = objectMapper.readValue(message, RebbitRequest.class);
        if(Integer.parseInt(coordinates.getStatus()) == 0){
            Coordinate sampleCoordinate = new Coordinate(20.0, 30.0);
        coordinateSenderService.sendCoordinate(sampleCoordinate);
        }
        System.out.println("Slanje zavrseno!");

    }
}
