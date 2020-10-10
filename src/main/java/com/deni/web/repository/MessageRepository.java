package com.deni.web.repository;

import com.deni.web.model.Message;
import com.deni.web.model.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> findAllByTopic(Topic topic);

    List<Message> findAllByTopicAndMessageIdAfter(Topic topic, Long messageId);

    void deleteAllByTopicTopicName(String topicName);
}
