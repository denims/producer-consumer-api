package com.deni.web.service;

import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Message;
import com.deni.web.model.Topic;
import com.deni.web.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final TopicService topicService;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(TopicService topicService, MessageRepository messageRepository) {
        this.topicService = topicService;
        this.messageRepository = messageRepository;
    }

    @Override
    public Message createMessage(String topicName, String messageToSave) throws TopicNotFoundException {
        Topic savedTopic = topicService.getTopicByName(topicName);
        if (savedTopic == null) {
            throw new TopicNotFoundException();
        }
        Message message = Message.builder()
                .messageFromProducer(messageToSave)
                .topic(savedTopic)
                .build();
        return messageRepository.save(message);
    }
}
