package com.deni.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProducerConsumerErrorResponse {
    private String message;
    private String status;
}
