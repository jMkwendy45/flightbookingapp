package com.clanAfrica.demo.flight.dto.response;

import com.clanAfrica.demo.flight.data.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public class FightListResponse  extends BaseResponse{
    List<Flight> flights;
    public FightListResponse(boolean isSuccessful, int responseCode, String message, LocalDateTime timeStamp , List<Flight> flights) {
        super(isSuccessful, responseCode, message, timeStamp);

        this.flights =flights;
    }
}
