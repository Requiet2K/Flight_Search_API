package com.flight.search.amadeus.service;

import com.flight.search.amadeus.dto.FlightDTO;
import com.flight.search.amadeus.dto.request.SearchRequest;

import java.util.List;

public interface FlightService {
    FlightDTO createFlight(FlightDTO theFlight);
    List<FlightDTO> getAllFlights();
    FlightDTO getFlightById(Long theFlightId);
    FlightDTO updateFlight(Long theFlightId, FlightDTO theNewFlight);
    void deleteFlightById(Long theFlightId);
    void deleteAllFlights();
    List<List<FlightDTO>> findMatchingFlights(SearchRequest request);
}
