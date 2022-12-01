package com.example.demo.configuration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.ConnectionFactory;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.newsFromBloodBank}")
    private String queue;
    
    @Value("${rabbitmq.queue.responseBloodSub.name}")
    private String responseBloodSubQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    
    @Value("${rabbitmq.routing.responseBloodSubscription.key}")
    private String routingResponseBloodSubscriptionKey;

    // spring bean for rabbitmq queue
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }
    
    @Bean
    public Queue ResponseBloodSubQueue(){
        return new Queue(responseBloodSubQueue);
    }

    // spring bean for rabbitmq exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    // binding between queue and exchange using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }
    
    @Bean
    public Binding jsonBinding(){
        return BindingBuilder
                .bind(ResponseBloodSubQueue())
                .to(exchange())
                .with(routingResponseBloodSubscriptionKey);
    }

// Spring boot autoconfiguration provides following beans
    // ConnectionFactory
    // RabbitTemplate
    // RabbitAdmin
}