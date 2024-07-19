package com.kadiraksoy.springbootrabbitmq.consumer;

import com.kadiraksoy.springbootrabbitmq.dto.User;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class RabbitMQJsonConsumer {

    //private static final Logger LOGGER= (Logger) LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public  void  consumeJsonMessage(User user){
        System.out.println("Recieved JSON message -> %s"+user.toString());
        //LOGGER.info(String.format("Recieved JSON message -> %s",user.toString()));
    }
}