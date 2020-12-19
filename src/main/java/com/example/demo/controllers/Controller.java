package com.example.demo.controllers;

import com.example.demo.services.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private KafkaTemplate<Object, Object> template;

    @Autowired
    public Controller(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") final KafkaTemplate<Object, Object> template) {
        this.template = template;
    }

    @PostMapping(path = "/send/foo/{what}")
    public String sendFoo(@PathVariable String what) {
        System.out.println("sending " + what);
        try {
            final ListenableFuture<SendResult<Object, Object>> resultFuture = template.send("test", what);
            final SendResult<Object, Object> result = resultFuture.get();
            System.out.println("SENT: "  + result.toString());
        } catch (Exception e) {
            System.out.println("sending FAILED");
            System.out.println(e.getMessage());
        }
        return "sent successfully";
    }

    @GetMapping
    public Integer getCounter() {
        return KafkaConsumer.counter;
    }
}
