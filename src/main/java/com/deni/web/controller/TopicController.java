package com.deni.web.controller;

import com.deni.web.exception.TopicAlreadyExistsException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Subscription;
import com.deni.web.model.Topic;
import com.deni.web.service.SubscriptionService;
import com.deni.web.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;
    private final SubscriptionService subscriptionService;

    public TopicController(TopicService topicService,
                           SubscriptionService subscriptionService) {
        this.topicService = topicService;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Topic> createTopics(@RequestBody String topicName) throws TopicAlreadyExistsException {
        return ResponseEntity.ok(topicService.createTopic(topicName));
    }

    @PutMapping("{topicName}/edit")
    public ResponseEntity<Topic> editTopics(@RequestBody String newTopicName,
                                            @PathVariable String topicName) throws TopicNotFoundException {
        return ResponseEntity.ok(topicService.editTopic(topicName, newTopicName));
    }

    @DeleteMapping("{topicName}/delete")
    public void deleteTopics(@PathVariable String topicName) {
        topicService.deleteTopic(topicName);
    }


    @PostMapping("{topicName}/subscribe")
    public Subscription subscribeToATopic(@RequestBody String consumerName,
                                          @PathVariable String topicName) throws TopicNotFoundException {
        return subscriptionService.subscribe(consumerName, topicName);
    }

}
