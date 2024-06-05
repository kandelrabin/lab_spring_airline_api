package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.Flight;
import com.example.airline_api.repositories.BookingRepository;
import com.example.airline_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    BookingRepository bookingRepository;

    public void addNewFlight(Flight flight){
        flightRepository.save(flight);
    }

    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
    }

    public Optional<Flight> getSingleFlight(Long id){
        return flightRepository.findById(id);
    }

    public void reduceFlightCapacity(Long id){
        Flight flight = getSingleFlight(id).get();
        flight.setCapacity(flight.getCapacity()-1);
        flightRepository.save(flight);
    }

    public void cancelFlight(Long id){
        Flight flight = getSingleFlight(id).get();
        for (Booking booking : flight.getBookings()){
            bookingRepository.deleteById(booking.getId());
        }
        flightRepository.deleteById(id);
    }


}
