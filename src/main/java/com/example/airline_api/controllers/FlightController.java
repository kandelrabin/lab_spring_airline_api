package com.example.airline_api.controllers;

import com.example.airline_api.models.Flight;
import com.example.airline_api.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    // INDEX and FILTER merged
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam Optional<String> destination){
        List<Flight> flights;
        if (destination.isEmpty()){
            flights = flightService.getAllFlights();
        } else {
            flights = flightService.filterFlightsByDestination(destination.get());
        }
            return new ResponseEntity<>(flights, HttpStatus.OK);
    }

    // SHOW
    @GetMapping(value = "/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id){
        Optional<Flight> flightOptional = flightService.getSingleFlight(id);
        if(flightOptional.isPresent()){
            return new ResponseEntity<>(flightOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Flight> addNewFlight(@RequestBody Map<String, Object> flightPayload){
        String destination = String.valueOf(flightPayload.get("destination"));
        int capacity = (int) flightPayload.get("capacity");
        LocalDate departureDate = LocalDate.parse(String.valueOf(flightPayload.get("departureDate")));
        LocalTime departureTime = LocalTime.parse(String.valueOf(flightPayload.get("departureTime")));
        Flight flight = new Flight(destination, capacity, departureDate, departureTime);
        flightService.addNewFlight(flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    // DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> cancelFlight(@PathVariable Long id){
        flightService.cancelFlight(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }




}
