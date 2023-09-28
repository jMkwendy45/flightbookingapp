package com.clanAfrica.demo.flight.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
public class BaseResponse {
    private boolean isSuccessful;
    private int responseCode;
    private String message;
    private LocalDateTime timeStamp = LocalDateTime.now();
}
