package com.deni.web.repository;

import com.deni.web.model.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {
    Topic findByTopicName(String topicName);

    void deleteByTopicName(String topicName);
}
