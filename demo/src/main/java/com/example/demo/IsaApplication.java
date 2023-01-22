package com.example.demo;

import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.io.InputStream;

import com.example.demo.service.EmailServiceImpl;


@SpringBootApplication
@EnableScheduling
public class IsaApplication {
	
	public static void main(String[] args) {
        	SpringApplication.run(IsaApplication.class, args);
    }

	/*
	 * @Bean public ModelMapper modelMapper() { return new ModelMapper(); }
	 * 
	 * @Value("${rabbitmq.queue.bloodSubscription}") private String queue;
	 * 
	 * @Value("${rabbitmq.exchange.bloodSubscription}") private String exchange;
	 * 
	 * @Value("${rabbitmq.routing.bloodSubscription}") private String routingKey;
	 * 
	 * @Bean public Queue queue(){ return new Queue(queue); }
	 * 
	 * @Bean public TopicExchange exchange(){ return new TopicExchange(exchange); }
	 * 
	 * // binding between queue and exchange using routing key
	 * 
	 * @Bean public Binding binding(){ return BindingBuilder .bind(queue())
	 * .to(exchange()) .with(routingKey); }
	 * 
	 * @Bean SimpleMessageListenerContainer container(ConnectionFactory
	 * connectionFactory, MessageListenerAdapter listenerAdapter) {
	 * SimpleMessageListenerContainer container = new
	 * SimpleMessageListenerContainer();
	 * container.setConnectionFactory(connectionFactory);
	 * container.setQueueNames(queue);
	 * container.setMessageListener(listenerAdapter); return container; }
	 * 
	 * @Bean MessageListenerAdapter listenerAdapter(Receiver receiver) {
	 * System.out.println("aaa"); return new MessageListenerAdapter(receiver,
	 * "receiveMessage"); }
	 */
}
