package com.example.demo.configuration;

import com.example.demo.model.BloodSupply;
import com.example.demo.repository.BloodSupplyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BloodSupplyConfig {

    @Bean
    CommandLineRunner commandLineRunner(BloodSupplyRepository repository){
        return args ->{
            BloodSupply A = new BloodSupply(
                "A",
                    10.0
            );
            BloodSupply B = new BloodSupply(
                    "B",
                    0.0
            );
            BloodSupply AB = new BloodSupply(
                    "AB",
                    5.0
            );
            BloodSupply O = new BloodSupply(
                    "O",
                    10.0
            );

            repository.saveAll(List.of(A,B,AB,O));
        };
    }
}
