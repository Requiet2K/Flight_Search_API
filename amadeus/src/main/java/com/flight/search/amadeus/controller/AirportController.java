package com.flight.search.amadeus.controller;

import com.flight.search.amadeus.dto.AirportDTO;
import com.flight.search.amadeus.service.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService theAirportService)
    {
        airportService = theAirportService;
    }

    @PostMapping("/create")
    public AirportDTO createAirport(@RequestBody AirportDTO airport) {
        return airportService.createAirport(airport);
    }

    @GetMapping("/{airportId}")
    public AirportDTO getAirport(@PathVariable Long airportId) {
        return airportService.getAirportById(airportId);
    }

    @GetMapping("/")
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }

    @PutMapping("/{airportId}")
    public AirportDTO updateAirport(@PathVariable Long airportId, @RequestBody AirportDTO theNewAirport) {
        return airportService.updateAirport(airportId, theNewAirport);
    }

    @DeleteMapping("/{airportId}")
    public void deleteAirportById(@PathVariable Long airportId) {
        airportService.deleteAirportById(airportId);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllAirports(){
        airportService.deleteAllAirports();
    }
}
