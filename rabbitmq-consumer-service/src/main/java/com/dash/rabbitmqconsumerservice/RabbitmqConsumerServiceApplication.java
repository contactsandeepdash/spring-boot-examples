package com.dash.rabbitmqconsumerservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RabbitmqConsumerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqConsumerServiceApplication.class, args);
	}

}
