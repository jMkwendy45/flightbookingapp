package com.clanAfrica.demo.flight.service;

import com.clanAfrica.demo.flight.data.model.Flight;
import com.clanAfrica.demo.flight.data.model.Reservation;
import com.clanAfrica.demo.flight.dto.request.RegisterFlightRequest;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    Flight registerFlight(RegisterFlightRequest registerFlightRequest);
    Flight findFlightById(Long flightId);
    List<Flight> findAllFlight();
    List<Flight> findByFLightDepartureDateCityAndDepatureTime(String departureCity, String departureDestination,
                                                             LocalDate departureDate);
    Reservation BookFlight(Long FlightId, Long userId);

}
