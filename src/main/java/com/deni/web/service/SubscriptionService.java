package com.deni.web.service;

import com.deni.web.exception.TopicNotFoundException;
import com.deni.web.model.Subscription;

public interface SubscriptionService {
    Subscription subscribe(String consumerName, String topicName) throws TopicNotFoundException;
}
