package com.flight.search.amadeus.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flight.search.amadeus.dto.AirportDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchRequest {
    private Long departureAirportId;
    private Long arrivalAirportId;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime departingTime;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime returningTime;
}
