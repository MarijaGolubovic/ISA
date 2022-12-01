package com.example.demo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQBloodSubscriptionProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.responseBloodSubscription.key}")
    private String routingResponseBloodSubKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQBloodSubscriptionProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQBloodSubscriptionProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(String json){
        LOGGER.info(String.format("Json message sent -> %s", json));
        rabbitTemplate.convertAndSend(exchange, routingResponseBloodSubKey, json);
    }
}
