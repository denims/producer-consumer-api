package com.deni.web.controller;

import com.deni.web.exception.ConsumerNotFoundException;
import com.deni.web.exception.SubscriptionNotFoundException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Message;
import com.deni.web.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    private final MessageService messageService;

    public ConsumerController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/{consumerName}/fetch")
    public ResponseEntity<List<Message>> fetchAllMessages(@PathVariable String consumerName,
                                                          @RequestParam String topicName)
            throws ConsumerNotFoundException, SubscriptionNotFoundException, TopicNotFoundException {

        return ResponseEntity.ok(messageService.getAllPendingMessages(topicName, consumerName));

    }

}
