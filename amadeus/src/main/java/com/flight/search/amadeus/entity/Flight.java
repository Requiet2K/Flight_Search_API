package com.flight.search.amadeus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "flight")
public class Flight {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport arrivalAirport;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime departingTime;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime returningTime;
    private BigDecimal price;
}
