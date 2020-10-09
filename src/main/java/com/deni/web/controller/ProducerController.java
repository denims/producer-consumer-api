package com.deni.web.controller;

import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Message;
import com.deni.web.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    private final MessageService messageService;

    public ProducerController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/produce/{topicName}/push-message")
    public ResponseEntity<Message> createMessage(@PathVariable String topicName,
                                                 @RequestBody String message) throws TopicNotFoundException {
        return ResponseEntity.ok(messageService.createMessage(topicName, message));
    }
}
