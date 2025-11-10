package com.message.queue.jms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final JmsTemplate jmsTemplate;
    private final String queueName;

    public MessageController(JmsTemplate jmsTemplate,
                             @Value("${jms.queue.name}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    @PostMapping
    public String sendMessage(@RequestBody String message) {
        jmsTemplate.convertAndSend(queueName, message);
        return "Message sent successfully!";
    }
}
