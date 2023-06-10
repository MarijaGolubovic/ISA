package com.example.demo.websocket;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoordinateGeneratorService {

    public List<Coordinate> generateCoordinates(double startLatitude, double startLongitude,
                                                double endLatitude, double endLongitude, int steps) {
        List<Coordinate> coordinates = new ArrayList<>();

        double latitudeStep = (endLatitude - startLatitude) / steps;
        double longitudeStep = (endLongitude - startLongitude) / steps;

        double currentLatitude = startLatitude;
        double currentLongitude = startLongitude;

        for (int i = 0; i <= steps; i++) {
            coordinates.add(new Coordinate(currentLatitude, currentLongitude));

            currentLatitude += latitudeStep;
            currentLongitude += longitudeStep;
        }

        return coordinates;
    }
}