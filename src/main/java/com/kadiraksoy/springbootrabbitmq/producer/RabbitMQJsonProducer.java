package com.kadiraksoy.springbootrabbitmq.producer;

import com.kadiraksoy.springbootrabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    // RabbitMQ'da kullanılacak değişim adı
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    // JSON mesajları için yönlendirme anahtarı
    @Value("${rabbitmq.routing.key.json}")
    private String jsonRountingKey;

    // Logger tanımlaması
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    // RabbitTemplate alanı
    private RabbitTemplate rabbitTemplate;

    // Constructor
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // JSON mesajı gönderme metodu
    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("JSON Messasge sent-> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, jsonRountingKey, user);
    }

}