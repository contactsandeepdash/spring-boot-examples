package com.dash.rabbitmqproducerservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class RabbitmqProducerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerServiceApplication.class, args);
	}

}
