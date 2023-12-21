package com.flight.search.amadeus.exception;

public class FlightNotFoundException extends RuntimeException{
    public FlightNotFoundException(){
        super("Flight not found!");
    }
}
