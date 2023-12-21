package com.flight.search.amadeus.controller;

import com.flight.search.amadeus.dto.FlightDTO;
import com.flight.search.amadeus.dto.request.SearchRequest;
import com.flight.search.amadeus.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService theFlightService){
        flightService = theFlightService;
    }

    @PostMapping("/create")
    public FlightDTO createFlight(@RequestBody FlightDTO theFlight){
        return flightService.createFlight(theFlight);
    }

    @GetMapping("/")
    public List<FlightDTO> getAllFlights(){
        return flightService.getAllFlights();
    }

    @GetMapping("/{flightId}")
    public FlightDTO getFlightById(@PathVariable Long flightId){
        return flightService.getFlightById(flightId);
    }

    @PutMapping("/{flightId}")
    public FlightDTO updateFlight(@PathVariable Long flightId, @RequestBody FlightDTO theNewFlight){
        return flightService.updateFlight(flightId, theNewFlight);
    }

    @DeleteMapping("/{flightId}")
    public void deleteFlightById(@PathVariable Long flightId){
        flightService.deleteFlightById(flightId);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllFlights(){
        flightService.deleteAllFlights();
    }

    @GetMapping("/availableFlights") // Search API
    public List<List<FlightDTO>> findMatchingFlights(@RequestBody SearchRequest request){
        return flightService.findMatchingFlights(request);
    }

}
