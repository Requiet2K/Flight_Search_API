package com.flight.search.amadeus.repository;

import com.flight.search.amadeus.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartingTime(
                Long departureAirportId, Long arrivalAirportId, LocalDateTime departingTime);

    List<Flight> findByDepartureAirportIdAndArrivalAirportIdAndDepartingTimeAndReturningTime(
                Long departureAirportId, Long arrivalAirportId, LocalDateTime departingTime, LocalDateTime returningTime);

}
