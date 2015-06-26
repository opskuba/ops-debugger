package com.opskuba.debugger.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class DebugMessageConsumer {
	
	@RabbitListener(queues = "test-queue")
    public void receiveMessage(String foo) {
        System.out.println("Received <" + foo + ">");
    }

}
