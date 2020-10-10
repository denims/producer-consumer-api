package com.deni.web.service;

import com.deni.web.exception.ConsumerNotFoundException;
import com.deni.web.model.Consumer;

public interface ConsumerService {
    Consumer getOrCreateConsumer(String consumerName);

    Consumer getConsumer(String consumerName) throws ConsumerNotFoundException;
}
