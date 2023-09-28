package com.clanAfrica.demo.flight.service;

import com.clanAfrica.demo.Exception.NotFoundException;
import com.clanAfrica.demo.flight.data.model.Flight;
import com.clanAfrica.demo.flight.data.model.Reservation;
import com.clanAfrica.demo.flight.data.repositories.FlightRepository;
import com.clanAfrica.demo.flight.data.repositories.ReservationRepository;
import com.clanAfrica.demo.flight.dto.request.RegisterFlightRequest;
import com.clanAfrica.demo.auth.data.models.User;
import com.clanAfrica.demo.auth.data.repositories.UserRepository;
import com.clanAfrica.demo.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClanFlightServiceImpl implements FlightService{
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Flight registerFlight(RegisterFlightRequest registerFlightRequest) {
        Flight flight = new Flight();
        flight.setFlightNumber(registerFlightRequest.getFlightNumber());
        flight.setAirLineType(registerFlightRequest.getAirLineType());
        flight.setTicketClass(registerFlightRequest.getTicketClass());
        flight.setNumberOfSeats(registerFlightRequest.getNumberOfSeats());
        flight.setDepartureTime(registerFlightRequest.getDepartureTime());
        flight.setDepartureCity(registerFlightRequest.getDepartureCity());
        flight.setDestinationCity(registerFlightRequest.getDestinationCity());
        flight.setAllOccupied(false);
        return  flightRepository.save(flight);
    }
    @Override
    public Flight findFlightById(Long flightId) {
         Optional<Flight> flight = flightRepository.findById(flightId);
         if (flight.isEmpty()){
             throw new NotFoundException("Flight not found");
         }
         else {
             return  flight.get();
         }
    }
    @Override
    public List<Flight> findAllFlight() {
        return flightRepository.findAll();
    }

    @Override
    public List<Flight> findByFLightDepartureDateCityAndDepatureTime(String departureCity, String departureDestination, LocalDate departureDate) {
        return flightRepository.findByDepartureCityAndDestinationCityAndDepartureDate(departureCity, departureDestination, departureDate);
    }

    @Override
    public Reservation BookFlight(Long flightId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Flight flight = findFlightById(flightId);
        if (flight.isAllOccupied()){
            throw new NotFoundException("Flight is all booked");
        }
        else {
            flight.setNumberOfAvailableSeats(flight.getNumberOfSeats()-1);
            Reservation reservation = new Reservation();
            reservation.setFlight(flight);
            reservation.setUser(user.get());
            if (flight.getNumberOfSeats()== 0){
                flight.setAllOccupied(true);
            }
            return reservationRepository.save(reservation);

        }

    }
}
