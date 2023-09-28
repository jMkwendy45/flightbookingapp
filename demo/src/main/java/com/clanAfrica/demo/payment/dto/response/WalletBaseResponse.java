package com.clanAfrica.demo.payment.dto.response;

import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import com.clanAfrica.demo.payment.model.Wallet;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
public class WalletBaseResponse extends BaseResponse {
    private Wallet wallet;

    public WalletBaseResponse(boolean isSuccessful, int responseCode, String message, LocalDateTime timeStamp, Wallet wallet) {
        super(isSuccessful, responseCode, message, timeStamp);
        this.wallet = wallet;
    }
}
