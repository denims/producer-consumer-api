package com.deni.web.service;

import com.deni.web.exception.TopicAlreadyExistsException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Topic;
import com.deni.web.repository.TopicRepository;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Log
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public Topic createTopic(String topicName) throws TopicAlreadyExistsException {
        if (topicRepository.findByTopicName(topicName) != null) {
            throw new TopicAlreadyExistsException();
        }
        Topic topic = Topic.builder()
                .topicName(topicName)
                .build();
        Topic createdTopic = topicRepository.save(topic);
        log.info("New topic created " + topicName);
        return createdTopic;
    }

    @Override
    public Topic editTopic(String oldName, String newName)
            throws TopicNotFoundException, TopicAlreadyExistsException {
        log.info("Updating topic names");
        Topic topic = topicRepository.findByTopicName(oldName);
        if (topic == null) {
            throw new TopicNotFoundException();
        }
        Topic existingTopic = topicRepository.findByTopicName(newName);
        if (existingTopic != null) {
            throw new TopicAlreadyExistsException();
        }
        topic.setTopicName(newName);
        return topicRepository.save(topic);
    }

    @Override
    @Transactional
    public void deleteTopic(String topicName) {
        log.info("Deleting topic " + topicName);
        topicRepository.deleteByTopicName(topicName);
    }

    @Override
    public Topic getTopicByName(String topic) throws TopicNotFoundException {
        Topic savedTopic = topicRepository.findByTopicName(topic);
        if (topic == null) {
            throw new TopicNotFoundException();
        }
        return savedTopic;
    }
}
