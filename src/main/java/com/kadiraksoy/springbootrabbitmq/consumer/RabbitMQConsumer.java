package com.kadiraksoy.springbootrabbitmq.consumer;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RabbitMQConsumer {

    // Mesajları loglamak için kullanılan logger
    ///private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(RabbitMQConsumer.class);

    // Mesaj alma metodu
    @RabbitListener(queues = {"${rabbitmq.queue.name}"}) // application.properties'deki kuyruktan mesaj dinler
    public void consume(String message) {
        // Gelen mesajı konsola yazdırır
        System.out.println("Mesaj alındı -> %s" + message);

        // Gelen mesajı loglar
       // LOGGER.info(String.format("Mesaj alındı -> %s", message));

        // TODO: Alınan mesajla ilgili işlemleri burada yapın
        // Örneğin: Veritabanına kaydetme, başka servislere iletme, işleme, vb.
    }
}