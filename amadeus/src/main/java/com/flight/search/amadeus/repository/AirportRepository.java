package com.flight.search.amadeus.repository;

import com.flight.search.amadeus.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
