package com.flight.search.amadeus.service.impl;

import com.flight.search.amadeus.dto.AirportDTO;
import com.flight.search.amadeus.entity.Airport;
import com.flight.search.amadeus.exception.AirportNotFoundException;
import com.flight.search.amadeus.repository.AirportRepository;
import com.flight.search.amadeus.service.AirportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public AirportServiceImpl(AirportRepository theAirportRepository, ModelMapper theModelMapper){
        airportRepository = theAirportRepository;
        modelMapper = theModelMapper;
    }

    @Override
    public AirportDTO createAirport(AirportDTO theAirport) {
        Airport savedAirport = airportRepository.save(modelMapper.map(theAirport, Airport.class));
        theAirport.setId(savedAirport.getId());
        return theAirport;
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll()
                .stream()
                .map(airport -> modelMapper.map(airport, AirportDTO.class))
                .toList();
    }

    @Override
    public AirportDTO getAirportById(Long theAirportId) {
        return airportRepository.findById(theAirportId)
                .map(airport -> modelMapper.map(airport, AirportDTO.class))
                .orElseThrow(AirportNotFoundException::new);
    }


    @Override
    public AirportDTO updateAirport(Long theAirportId, AirportDTO theNewAirport) {
        return airportRepository.findById(theAirportId)
                .map(foundAirport -> {
                    foundAirport.setCity(theNewAirport.getCity());
                    airportRepository.save(foundAirport);
                    return modelMapper.map(foundAirport, AirportDTO.class);
                })
                .orElseThrow(AirportNotFoundException::new);
    }

    @Override
    public void deleteAirportById(Long theAirportId) {
        airportRepository.findById(theAirportId)
                .ifPresentOrElse(
                        foundFlight -> airportRepository.deleteById(theAirportId),
                        () -> {
                            throw new AirportNotFoundException();
                        }
                );
    }

    @Override
    public void deleteAllAirports() {
        airportRepository.deleteAll();
    }
}
