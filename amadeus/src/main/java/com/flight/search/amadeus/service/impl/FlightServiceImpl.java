package com.flight.search.amadeus.service.impl;

import com.flight.search.amadeus.dto.FlightDTO;
import com.flight.search.amadeus.dto.request.SearchRequest;
import com.flight.search.amadeus.entity.Airport;
import com.flight.search.amadeus.entity.Flight;
import com.flight.search.amadeus.exception.AirportNotFoundException;
import com.flight.search.amadeus.exception.FlightNotFoundException;
import com.flight.search.amadeus.repository.AirportRepository;
import com.flight.search.amadeus.repository.FlightRepository;
import com.flight.search.amadeus.service.FlightService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final ModelMapper modelMapper;

    public FlightServiceImpl(FlightRepository theFlightRepository,
                             AirportRepository theAirportRepository,
                             ModelMapper theModelMapper){
        flightRepository = theFlightRepository;
        airportRepository = theAirportRepository;
        modelMapper = theModelMapper;
    }

    @Override
    public FlightDTO createFlight(FlightDTO theFlight) {
        Airport departureAirport = airportRepository.findById(theFlight.getDepartureAirport().getId())
                .orElseThrow(AirportNotFoundException::new);

        Airport arrivalAirport = airportRepository.findById(theFlight.getArrivalAirport().getId())
                .orElseThrow(AirportNotFoundException::new);

        theFlight.setDepartureAirport(departureAirport);
        theFlight.setArrivalAirport(arrivalAirport);

        Flight savedFlight = flightRepository.save(modelMapper.map(theFlight, Flight.class));

        theFlight.setId(savedFlight.getId());

        return theFlight;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .toList();
    }

    @Override
    public FlightDTO getFlightById(Long theFlightId) {
        return flightRepository.findById(theFlightId)
                .map(flight -> modelMapper.map(flight, FlightDTO.class))
                .orElseThrow(FlightNotFoundException::new);
    }

    @Override
    public FlightDTO updateFlight(Long theFlightId, FlightDTO theNewFlight) {
        return flightRepository.findById(theFlightId)
                .map(foundFlight -> {
                    foundFlight.setDepartureAirport(theNewFlight.getDepartureAirport());
                    foundFlight.setArrivalAirport(theNewFlight.getArrivalAirport());
                    foundFlight.setDepartingTime(theNewFlight.getDepartingTime());
                    foundFlight.setReturningTime(theNewFlight.getReturningTime());
                    foundFlight.setPrice(theNewFlight.getPrice());

                    flightRepository.save(foundFlight);
                    return modelMapper.map(foundFlight, FlightDTO.class);
                })
                .orElseThrow(FlightNotFoundException::new);
    }

    @Override
    public void deleteFlightById(Long theFlightId) {
        flightRepository.findById(theFlightId)
                .ifPresentOrElse(
                        foundFlight -> flightRepository.deleteById(theFlightId),
                        () -> {
                            throw new FlightNotFoundException();
                        }
                );
    }

    @Override
    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }

    @Override // Search API
    public List<List<FlightDTO>> findMatchingFlights(SearchRequest request) {
        if (request.getReturningTime() == null) {
            return List.of(
                    flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartingTime(
                                    request.getDepartureAirportId(), request.getArrivalAirportId(), request.getDepartingTime())
                            .stream()
                            .map(flight -> modelMapper.map(flight, FlightDTO.class))
                            .toList()
            );
        } else {
            List<FlightDTO> departingFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartingTime(
                            request.getDepartureAirportId(), request.getArrivalAirportId(), request.getDepartingTime())
                    .stream()
                    .map(flight -> modelMapper.map(flight, FlightDTO.class))
                    .toList();

            List<FlightDTO> returningFlights = flightRepository.findByDepartureAirportIdAndArrivalAirportIdAndDepartingTimeAndReturningTime(
                            request.getDepartureAirportId(), request.getArrivalAirportId(), request.getDepartingTime(), request.getReturningTime())
                    .stream()
                    .map(flight -> modelMapper.map(flight, FlightDTO.class))
                    .toList();

            return List.of(departingFlights, returningFlights);
        }
    }



}
