package com.deni.web.service;

import com.deni.web.exception.ConsumerNotFoundException;
import com.deni.web.exception.SubscriptionNotFoundException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Message;

import java.util.List;

public interface MessageService {
    Message createMessage(String topicName, String message) throws TopicNotFoundException;

    List<Message> getAllPendingMessages(String topicName, String consumerName) throws TopicNotFoundException, ConsumerNotFoundException, SubscriptionNotFoundException;
}
