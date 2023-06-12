package com.example.demo.websocket;

import com.example.demo.model.BloodSupply;
import com.example.demo.service.BloodBankService;
import com.example.demo.service.BloodSupplyService;
import com.example.demo.service.RefreshPeriodService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class CoordinateSenderService {

    private final RabbitTemplate rabbitTemplate;
    private final CoordinateGeneratorService coordinateGeneratorService;
    private final ObjectMapper objectMapper;
    private final RefreshPeriodService refreshPeriodService;
   private final UserService userService;
   private final BloodBankService bloodBankService;
   private  final BloodSupplyService bloodSupplyService;

    @Autowired
    public CoordinateSenderService(RabbitTemplate rabbitTemplate, CoordinateGeneratorService coordinateGeneratorService, ObjectMapper objectMapper, RefreshPeriodService refreshPeriodService,
                                   UserService userService, BloodBankService bloodBankService, BloodSupplyService bloodSupplyService) {
        this.rabbitTemplate = rabbitTemplate;
        this.coordinateGeneratorService = coordinateGeneratorService;
        this.objectMapper = objectMapper;
        this.refreshPeriodService = refreshPeriodService;
        this.userService = userService;
        this.bloodBankService = bloodBankService;
        this.bloodSupplyService = bloodSupplyService;
    }

    public void sendCoordinate(double startLatitude, double startLongitude, int bloodUnist) throws JsonProcessingException, ExecutionException, InterruptedException {
        bloodBankService.changeBloodUnits(Long.valueOf(1),bloodUnist);

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
        int time =2;
        for (Coordinate c: coord){
            time = refreshPeriodService.findRefreshPeriodAsync(userService.getAuthUser()).get();
            String jsonCoordinate = objectMapper.writeValueAsString(c);
            rabbitTemplate.convertAndSend("vehicle-coordinates-queue", jsonCoordinate);
            sleep(time);
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