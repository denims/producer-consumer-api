package com.deni.web.service;

import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Consumer;
import com.deni.web.model.Subscription;
import com.deni.web.model.Topic;
import com.deni.web.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private final TopicService topicService;
    private final ConsumerService consumerService;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(TopicService topicService,
                                   ConsumerService consumerService,
                                   SubscriptionRepository subscriptionRepository) {
        this.topicService = topicService;
        this.consumerService = consumerService;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Subscription subscribe(String consumerName, String topicName) throws TopicNotFoundException {
        Consumer consumer = consumerService.getOrCreateConsumer(consumerName);
        Topic topic = topicService.getTopicByName(topicName);
        Subscription savedSubscription = subscriptionRepository.findByTopicAndConsumer(topic, consumer);

        if (savedSubscription == null) {
            Subscription newSubscription = Subscription.builder()
                    .consumer(consumer)
                    .topic(topic)
                    .build();
            savedSubscription = subscriptionRepository.save(newSubscription);
        }

        return savedSubscription;

    }
}
