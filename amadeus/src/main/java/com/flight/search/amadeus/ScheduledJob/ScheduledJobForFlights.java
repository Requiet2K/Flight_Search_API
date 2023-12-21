package com.flight.search.amadeus.ScheduledJob;

import com.flight.search.amadeus.dto.FlightDTO;
import com.flight.search.amadeus.exception.FlightNotFoundException;
import com.flight.search.amadeus.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ScheduledJobForFlights {

    @Value("${mock-api.url}")
    private String mockApiUrl;

    private final FlightService flightService;

    @Autowired
    public ScheduledJobForFlights(FlightService flightService) {
        this.flightService = flightService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Getting triggered every midnight
    public void triggerScheduledJob() {
        invokeMockApiForScheduledJob();
    }

    private void invokeMockApiForScheduledJob() {
        RestTemplate restTemplate = new RestTemplate();

        // Getting flights from Mock Api
        ResponseEntity<List<FlightDTO>> response = restTemplate.exchange(
                mockApiUrl + "/getFlights",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FlightDTO>>() {}
        );

        List<FlightDTO> flights = response.getBody();
        // Saving flights into db whose have extracted from Mock API
        if(flights == null){
            throw new FlightNotFoundException();
        }else{
            for (FlightDTO flight : flights) {
                flightService.createFlight(flight);
            }
        }
    }
}
