package com.flight.search.amadeus.service;

import com.flight.search.amadeus.dto.AirportDTO;

import java.util.List;

public interface AirportService {
    AirportDTO createAirport(AirportDTO theAirport);
    List<AirportDTO> getAllAirports();
    AirportDTO getAirportById(Long theAirportId);
    AirportDTO updateAirport(Long theAirportId, AirportDTO theNewAirport);
    void deleteAirportById(Long theAirportId);
    void deleteAllAirports();
}
