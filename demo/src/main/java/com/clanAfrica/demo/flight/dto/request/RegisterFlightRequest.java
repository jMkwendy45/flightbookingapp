package com.clanAfrica.demo.flight.dto.request;

import com.clanAfrica.demo.flight.enums.AirLines;
import com.clanAfrica.demo.flight.enums.TicketClass;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Setter
@Getter
public class RegisterFlightRequest {
    private String flightNumber;
    private TicketClass ticketClass;
    private AirLines airLineType;
    private Integer numberOfSeats;
    private String departureCity;
    private LocalDate departureDate;
    private String destinationCity;
    private Time departureTime;
}
