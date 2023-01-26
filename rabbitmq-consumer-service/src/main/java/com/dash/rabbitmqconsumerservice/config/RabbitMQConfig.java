package com.dash.rabbitmqconsumerservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Autowired
    private RabbitMQProperties rabbitMQProperties;

    @Bean
    Queue queue() {
        return new Queue(rabbitMQProperties.getQueueName(), true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(rabbitMQProperties.getExchangeName());
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue()).to(exchange()).with(rabbitMQProperties.getRoutingKey());
    }

    // @Bean
    // SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
    //         MessageListenerAdapter listenerAdapter) {
    //     SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    //     container.setConnectionFactory(connectionFactory);
    //     container.setQueueNames(rabbitMQProperties.getQueueName());
    //     container.setMessageListener(listenerAdapter);
    //     return container;
    // }

    // @Bean
    // public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
    //     return new MappingJackson2MessageConverter();
    // }

    // @Bean
    // public RabbitTemplate amqpTemplate(ConnectionFactory connectionFactory) {
    //     final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //     rabbitTemplate.setMessageConverter(messageConverter());
    //     return rabbitTemplate;
    // }

    // @Bean
    // public Jackson2JsonMessageConverter messageConverter() {
    //     return new Jackson2JsonMessageConverter();
    // }

    // @Bean
    // public SimpleMessageConverter messageConverter() {
    //     return new SimpleMessageConverter();
    // }

    // @Bean
    // MessageListenerAdapter listenerAdapter(RabbitMQConsumer listener) {
    //     return new MessageListenerAdapter(listener, "listen");
    // }

}
