package com.example.demo.controller;

import com.example.demo.websocket.Coordinate;
import com.example.demo.websocket.CoordinateSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/coordinates")
public class CoordinateSenderController {

    private final CoordinateSenderService coordinateSenderService;

    public CoordinateSenderController(CoordinateSenderService coordinateSenderService) {
        this.coordinateSenderService = coordinateSenderService;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PreAuthorize("permitAll()")
    @GetMapping("/send-sample-coordinate")
    public void sendSampleCoordinate() throws JsonProcessingException {
        // Kreiranje testnih koordinata
        Coordinate sampleCoordinate = new Coordinate(20.0, 30.0);

        // Slanje testnih koordinata RabbitMQ redom
        coordinateSenderService.sendCoordinate(sampleCoordinate);
    }
}
