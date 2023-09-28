package com.clanAfrica.demo.flight.data.repositories;

import com.clanAfrica.demo.flight.data.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByDepartureCityAndDestinationCityAndDepartureDate(String departureCity, String destinationCity, LocalDate date);
}
