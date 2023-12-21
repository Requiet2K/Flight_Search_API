package com.flight.search.amadeus.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airport")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Airport {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
}
