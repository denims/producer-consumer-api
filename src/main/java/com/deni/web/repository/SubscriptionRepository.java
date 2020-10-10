package com.deni.web.repository;

import com.deni.web.model.Consumer;
import com.deni.web.model.Subscription;
import com.deni.web.model.Topic;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    Subscription findByTopicAndConsumer(Topic topic, Consumer consumer);

    void deleteAllByTopicTopicName(String topic);
}
