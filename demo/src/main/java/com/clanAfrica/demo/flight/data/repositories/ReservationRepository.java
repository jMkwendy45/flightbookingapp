package com.clanAfrica.demo.flight.data.repositories;

import com.clanAfrica.demo.flight.data.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
