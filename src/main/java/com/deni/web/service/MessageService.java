package com.deni.web.service;

import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Message;

public interface MessageService {
    Message createMessage(String topicName, String message) throws TopicNotFoundException;
}
