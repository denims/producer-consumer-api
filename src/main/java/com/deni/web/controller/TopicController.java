package com.deni.web.controller;

import com.deni.web.exception.TopicAlreadyExistsException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Topic;
import com.deni.web.service.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
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

}
