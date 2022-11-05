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
        };
    }
}
