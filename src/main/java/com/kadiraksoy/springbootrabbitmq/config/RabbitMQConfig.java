package com.kadiraksoy.springbootrabbitmq.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Değerleri application.properties dosyasından okur
    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.key.json}")
    private String routingKeyJSON;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueue;

    // RabbitMQ kuyruğu oluşturur
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    // JSON Mesajları için kuyruk oluşturur
    @Bean("${rabbitmq.json.queue.name}")
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    // RabbitMQ Topic Exchange oluşturur
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    // Kuyruğu exchange'e bağlar
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // JSON kuyruğunu exchange'e bağlar
    @Bean
    public Binding bindingJSON() {
        return BindingBuilder.bind(jsonQueue())
                .to(exchange())
                .with(routingKeyJSON);
    }

    // JSON mesajları için MessageConverter oluşturur
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    // Mesaj göndermek için AmqpTemplate oluşturur
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}