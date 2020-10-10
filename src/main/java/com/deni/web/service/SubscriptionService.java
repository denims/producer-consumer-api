package com.deni.web.service;

import com.deni.web.exception.ConsumerNotFoundException;
import com.deni.web.exception.SubscriptionNotFoundException;
import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Subscription;
import com.deni.web.model.Topic;

public interface SubscriptionService {
    Subscription subscribe(String consumerName, String topicName) throws TopicNotFoundException;

    Subscription getSubscription(String consumerName, Topic topicName)
            throws ConsumerNotFoundException, SubscriptionNotFoundException;

    void updateSubscription(Subscription subscription);
}
