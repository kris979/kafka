package com.example.demo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    public static int counter = 0;

    @KafkaListener(topics = "test", groupId = "group_id")
    public void consume(String msg) {
        counter++;
        System.out.println("Consumed msg: " + msg);
    }

}
