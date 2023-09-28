package com.clanAfrica.demo.flighttest;

import com.clanAfrica.demo.Exception.NotFoundException;
import com.clanAfrica.demo.auth.data.models.User;
import com.clanAfrica.demo.auth.data.repositories.UserRepository;
import com.clanAfrica.demo.flight.data.model.Flight;
import com.clanAfrica.demo.flight.data.model.Reservation;
import com.clanAfrica.demo.flight.data.repositories.FlightRepository;
import com.clanAfrica.demo.flight.data.repositories.ReservationRepository;
import com.clanAfrica.demo.flight.dto.request.RegisterFlightRequest;
import com.clanAfrica.demo.flight.enums.AirLines;
import com.clanAfrica.demo.flight.enums.TicketClass;
import com.clanAfrica.demo.flight.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FlightTest {
    @Autowired
    FlightService flightService;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    FlightRepository flightRepository;

    private  RegisterFlightRequest registerFlightRequest;
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        registerFlightRequest = new RegisterFlightRequest();
        registerFlightRequest.setAirLineType(AirLines.IBOM_AIR);
        registerFlightRequest.setFlightNumber("A734T");
        registerFlightRequest.setDepartureCity("Lagos");
        registerFlightRequest.setDestinationCity("AkwaiIbom");
        registerFlightRequest.setDepartureTime(Time.valueOf(LocalTime.MIDNIGHT));
        registerFlightRequest.setTicketClass(TicketClass.BUSINESS_CLASS);
        registerFlightRequest.setDepartureDate(LocalDate.of(5,9,20));
        flightService.registerFlight(registerFlightRequest);




        User user = new User();
        user.setEmail("oluchi@gmail.com3");
    }

    @Test
    public void testToRegisterAFlight(){
        assertNotNull(registerFlightRequest);
    }
    @Test
    public void testToFindFlightBYid(){
        Flight foundFlight = flightService.findFlightById(52L);
        assertEquals(52L,foundFlight.getId());
    }

    @Test
    public void testToFindAllFlight(){
        RegisterFlightRequest registerFlightRequest2 = new RegisterFlightRequest();
        registerFlightRequest.setAirLineType(AirLines.IBOM_AIR);
        registerFlightRequest.setFlightNumber("A734R");
        registerFlightRequest.setDepartureCity("Lagos");
        registerFlightRequest.setDestinationCity("AkwaiIbom");
        registerFlightRequest.setDepartureTime(Time.valueOf(LocalTime.MIDNIGHT));
        registerFlightRequest.setTicketClass(TicketClass.BUSINESS_CLASS);
        flightService.registerFlight(registerFlightRequest2);

       List<Flight> foundFlights = flightService.findAllFlight();
        assertEquals(2,foundFlights.size());
    }

    @Test
    public void testTofindByFLightDepartureDateCityAndDepatureTime(){
        RegisterFlightRequest registerFlightRequest2 = new RegisterFlightRequest();
        registerFlightRequest.setAirLineType(AirLines.IBOM_AIR);
        registerFlightRequest.setFlightNumber("A734R");
        registerFlightRequest.setDepartureCity("Lagos");
        registerFlightRequest.setDestinationCity("AkwaiIbom");
        registerFlightRequest.setDepartureTime(Time.valueOf(LocalTime.MIDNIGHT));
        registerFlightRequest.setTicketClass(TicketClass.BUSINESS_CLASS);
        registerFlightRequest.setDepartureDate(LocalDate.of(5,9,20));
        flightService.registerFlight(registerFlightRequest2);

        RegisterFlightRequest registerFlightRequest3 = new RegisterFlightRequest();
        registerFlightRequest.setAirLineType(AirLines.IBOM_AIR);
        registerFlightRequest.setFlightNumber("A734R");
        registerFlightRequest.setDepartureCity("Abuja");
        registerFlightRequest.setDestinationCity("AkwaiIbom");
        registerFlightRequest.setDepartureTime(Time.valueOf(LocalTime.MIDNIGHT));
        registerFlightRequest.setTicketClass(TicketClass.BUSINESS_CLASS);
        registerFlightRequest.setDepartureDate(LocalDate.of(4,9,20));
        flightService.registerFlight(registerFlightRequest3);

    List<Flight>availableFlights = flightService.findByFLightDepartureDateCityAndDepatureTime("Abuja",
                "AkwaiIbom",LocalDate.of(4,9,20));
        assertEquals(2,availableFlights.size());
    }




}