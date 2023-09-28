package com.clanAfrica.demo.payment.dto.response;

import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class WebHookResponse extends BaseResponse {
    private Map<String, Object> response;

    public WebHookResponse(boolean isSuccessful, int responseCode, String message, LocalDateTime timeStamp, Map<String, Object> response) {
        super(isSuccessful, responseCode, message, timeStamp);
        this.response = response;
    }
}
