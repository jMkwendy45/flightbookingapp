package com.clanAfrica.demo.auth.dto.response;

import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthResponse extends BaseResponse {
    private AuthenticationResponse response;

    public AuthResponse(boolean isSuccessful, int responseCode, String message, LocalDateTime timeStamp, AuthenticationResponse response) {
        super(isSuccessful, responseCode, message, timeStamp);
        this.response = response;

    }
}
