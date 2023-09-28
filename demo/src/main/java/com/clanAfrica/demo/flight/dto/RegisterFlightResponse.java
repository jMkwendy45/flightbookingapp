package com.clanAfrica.demo.flight.dto;

import com.clanAfrica.demo.flight.data.model.Flight;
import com.clanAfrica.demo.flight.dto.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterFlightResponse  extends BaseResponse {
  private Flight flight;

    public RegisterFlightResponse(boolean isSuccessful, int responseCode, String message, LocalDateTime timeStamp,
                                  Flight flight) {
        super(isSuccessful, responseCode, message, timeStamp);
        flight =this.flight;
    }

}
