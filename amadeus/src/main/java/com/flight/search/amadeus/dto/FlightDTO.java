package com.flight.search.amadeus.dto;

import com.flight.search.amadeus.entity.Airport;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FlightDTO {
    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departingTime;
    private LocalDateTime returningTime;
    private BigDecimal price;
}
