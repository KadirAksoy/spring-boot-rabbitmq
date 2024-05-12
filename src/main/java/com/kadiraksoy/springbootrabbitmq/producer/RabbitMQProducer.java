package com.kadiraksoy.springbootrabbitmq.producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    // application.properties dosyasından okunan exchange ismi
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // application.properties dosyasından okunan routing key
    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    // Mesajları loglamak için kullanılan logger
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    // Mesaj göndermek için kullanılan RabbitTemplate
    private final RabbitTemplate rabbitTemplate;

    // Constructor, RabbitTemplate nesnesini parametre olarak alıp kaydeder.
    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Mesaj gönderme metodu
    public void sendMessage(String message) {
        // Gönderilen mesajı loglar
        LOGGER.info(String.format("Mesaj gönderildi -> %s", message));

        // Mesajı exchange'e ve routing key'e göre gönderir
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}


