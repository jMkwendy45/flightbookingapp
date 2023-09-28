package com.clanAfrica.demo.auth.dto.response;

import com.clanAfrica.demo.auth.data.models.User;
import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class RegisterUserResponse extends BaseResponse {
    private User user;

    public RegisterUserResponse(boolean isSuccessful, int responseCode, String message, LocalDateTime timeStamp, User user) {
        super(isSuccessful, responseCode, message, timeStamp);
        this.user = user;
    }
}
