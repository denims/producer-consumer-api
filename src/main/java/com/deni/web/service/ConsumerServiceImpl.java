package com.deni.web.service;

import com.deni.web.exception.ConsumerNotFoundException;
import com.deni.web.model.Consumer;
import com.deni.web.repository.ConsumerRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    private final ConsumerRepository consumerRepository;

    public ConsumerServiceImpl(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public Consumer getOrCreateConsumer(String consumerName) {
        Consumer savedConsumer = consumerRepository.findByConsumerName(consumerName);
        if (savedConsumer == null) {
            Consumer newConsumer = Consumer.builder()
                    .consumerName(consumerName)
                    .build();
            savedConsumer = consumerRepository.save(newConsumer);
        }
        return savedConsumer;
    }

    @Override
    public Consumer getConsumer(String consumerName) throws ConsumerNotFoundException {
        Consumer savedConsumer = consumerRepository.findByConsumerName(consumerName);
        if (savedConsumer == null) {
            throw new ConsumerNotFoundException();
        }
        return savedConsumer;
    }

}
