package com.flight.search.amadeus.exception;

public class AirportNotFoundException extends RuntimeException{
    public AirportNotFoundException(){
        super("Airport not found!");
    }
}
