package com.deni.web.service;

import com.deni.web.exception.ConsumerNotFoundException;
import com.deni.web.exception.SubscriptionNotFoundException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Message;
import com.deni.web.model.Subscription;
import com.deni.web.model.Topic;
import com.deni.web.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    private final TopicService topicService;
    private final ConsumerService consumerService;
    private final SubscriptionService subscriptionService;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(TopicService topicService,
                              ConsumerService consumerService,
                              SubscriptionService subscriptionService,
                              MessageRepository messageRepository) {
        this.topicService = topicService;
        this.consumerService = consumerService;
        this.subscriptionService = subscriptionService;
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

    @Override
    @Transactional
    public List<Message> getAllPendingMessages(String topicName, String consumerName)
            throws TopicNotFoundException, ConsumerNotFoundException, SubscriptionNotFoundException {

        List<Message> pendingMessages = new ArrayList<>();

        Topic savedTopic = topicService.getTopicByName(topicName);
        Subscription savedSubscription = subscriptionService.getSubscription(consumerName, savedTopic);
        Message lastMessage = savedSubscription.getLastMessage();

        if (lastMessage == null) {
            pendingMessages.addAll(messageRepository.findAllByTopic(savedTopic));
        } else {
            pendingMessages.addAll(messageRepository.findAllByTopicAndMessageIdAfter(savedTopic, lastMessage.getMessageId()));
        }
        if (!pendingMessages.isEmpty()) {
            Message lastMessageSaved = pendingMessages.get(pendingMessages.size() - 1);
            savedSubscription.setLastMessage(lastMessageSaved);
        }

        subscriptionService.updateSubscription(savedSubscription);

        return pendingMessages;
    }
}
