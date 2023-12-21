package com.flight.search.amadeus.ScheduledJob;

import com.flight.search.amadeus.dto.FlightDTO;
import com.flight.search.amadeus.entity.Airport;
import com.flight.search.amadeus.exception.AirportNotFoundException;
import com.flight.search.amadeus.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/api/mock-api")
public class MockController {

    private final AirportRepository airportRepository;

    @Autowired
    public MockController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @GetMapping("/getFlights")
    public ResponseEntity<List<FlightDTO>> getFlights() {
        List<FlightDTO> flights = generateMockFlights();
        return ResponseEntity.ok(flights);
    }

    private List<FlightDTO> generateMockFlights() {

        List<Airport> airports = airportRepository.findAll();

        if(airports.size() < 5){
            throw new AirportNotFoundException();
        }

        List<FlightDTO> flights = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            Random random = new Random();
            Long randomNumber = random.nextLong(Integer.MAX_VALUE);

            FlightDTO flight = new FlightDTO();
            flight.setId(randomNumber);
            flight.setDepartureAirport(airports.get(i));
            flight.setArrivalAirport(airports.get(i));
            flight.setDepartingTime(LocalDateTime.now().plusDays(i));
            flight.setReturningTime(flight.getDepartingTime().plusHours(3));
            flight.setPrice(BigDecimal.valueOf((500*i)));

            flights.add(flight);
        }
        return flights;
    }
}
