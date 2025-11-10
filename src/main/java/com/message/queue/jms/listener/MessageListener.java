package com.message.queue.jms.listener;

import com.message.queue.jms.model.Message;
import com.message.queue.jms.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MessageListener {

    private final MessageRepository messageRepository;

    public MessageListener(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @JmsListener(destination = "${jms.queue.name}", containerFactory = "jmsListenerContainerFactory")
    @Transactional
    public void receiveMessage(String messageContent) {
        System.out.println("Received message: " + messageContent);
        Message message = new Message();
        message.setContent(messageContent);
        messageRepository.save(message);
        System.out.println("Message saved successfully!");
    }
}
