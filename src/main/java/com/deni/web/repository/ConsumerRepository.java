package com.deni.web.repository;

import com.deni.web.model.Consumer;
import org.springframework.data.repository.CrudRepository;

public interface ConsumerRepository extends CrudRepository<Consumer, Long> {
    Consumer findByConsumerName(String consumerName);
}
