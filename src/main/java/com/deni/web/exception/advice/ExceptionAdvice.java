package com.deni.web.exception.advice;

import com.deni.web.constants.ProducerConsumerConstants;
import com.deni.web.dto.ProducerConsumerErrorResponse;
import com.deni.web.exception.ConsumerNotFoundException;
import com.deni.web.exception.SubscriptionNotFoundException;
import com.deni.web.exception.TopicAlreadyExistsException;
import com.deni.web.exception.TopicNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TopicAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ProducerConsumerErrorResponse handleTopicsAlreadyPresent(Exception exception) {

        return new ProducerConsumerErrorResponse(ProducerConsumerConstants.TOPIC_ALREADY_PRESENT,
                ProducerConsumerConstants.BAD_REQUEST);
    }

    @ExceptionHandler(TopicNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ProducerConsumerErrorResponse handleTopicsNotFound(Exception exception) {

        return new ProducerConsumerErrorResponse(ProducerConsumerConstants.TOPIC_NOT_FOUND,
                ProducerConsumerConstants.BAD_REQUEST);
    }

    @ExceptionHandler(ConsumerNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ProducerConsumerErrorResponse handleConsumerNotFound(Exception exception) {

        return new ProducerConsumerErrorResponse(ProducerConsumerConstants.CONSUMER_NOT_FOUND,
                ProducerConsumerConstants.BAD_REQUEST);
    }

    @ExceptionHandler(SubscriptionNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ProducerConsumerErrorResponse handleSubscriptionNotFound(Exception exception) {

        return new ProducerConsumerErrorResponse(ProducerConsumerConstants.SUBSCRIPTION_NOT_FOUND,
                ProducerConsumerConstants.BAD_REQUEST);
    }
}
