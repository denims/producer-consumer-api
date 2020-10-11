package com.deni.web.controller;

import com.deni.web.model.Message;
import com.deni.web.service.MessageService;
import com.deni.web.service.SubscriptionService;
import com.deni.web.service.TopicService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class ConsumerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MessageService messageService;
    @MockBean
    private TopicService topicService;
    @MockBean
    private SubscriptionService subscriptionService;

    @Test
    void givenPendingMessagesPresentForASubscriptionShouldSendItBackWhenCallingFetchEndpoint() throws Exception {
        List<Message> messages = new ArrayList<>();
        messages.add(Message.builder()
                .messageFromProducer("hello")
                .build());

        when(messageService.getAllPendingMessages(any(), any()))
                .thenReturn(messages);

        mockMvc.perform(get("/consumer/test-consumer/fetch")
                .param("topicName", "testTopic"))
                .andExpect(status().isOk())
                .andExpect(jsonPath(".messageFromProducer")
                        .value("hello"))
                .andReturn();
    }
}