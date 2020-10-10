package com.deni.web.service;

import com.deni.web.model.Consumer;

public interface ConsumerService {
    Consumer getOrCreateConsumer(String consumerName);
}
