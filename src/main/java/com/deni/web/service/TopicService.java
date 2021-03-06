package com.deni.web.service;

import com.deni.web.exception.TopicAlreadyExistsException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Topic;

public interface TopicService {
    Topic createTopic(String topicName) throws TopicAlreadyExistsException;

    Topic editTopic(String oldName, String newName) throws TopicNotFoundException, TopicAlreadyExistsException;

    void deleteTopic(String topicName);

    Topic getTopicByName(String topic) throws TopicNotFoundException;
}
