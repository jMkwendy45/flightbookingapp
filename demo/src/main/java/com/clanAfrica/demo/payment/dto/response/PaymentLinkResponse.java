package com.clanAfrica.demo.payment.dto.response;

import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import com.clanAfrica.demo.flight.enums.AirLines;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentLinkResponse extends BaseResponse {
    private String paymentLink;


    public PaymentLinkResponse(boolean isSuccessful, int responseCode, String message, LocalDateTime timeStamp, String paymentLink) {
        super(isSuccessful, responseCode, message, timeStamp);
        this.paymentLink =paymentLink;
    }
}
