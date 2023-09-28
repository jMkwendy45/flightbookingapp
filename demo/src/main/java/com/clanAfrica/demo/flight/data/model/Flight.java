package com.clanAfrica.demo.flight.data.model;

import com.clanAfrica.demo.flight.enums.AirLines;
import com.clanAfrica.demo.flight.enums.TicketClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String flightNumber;
    private TicketClass ticketClass;
    private AirLines airLineType;
    private Integer numberOfSeats;
    private Integer numberOfAvailableSeats;
    private String departureCity;
    private LocalDate departureDate;
    private String destinationCity;
    private Time departureTime;
    private boolean isAllOccupied;


}
