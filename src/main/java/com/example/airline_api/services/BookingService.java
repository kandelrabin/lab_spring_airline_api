package com.example.airline_api.services;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import com.example.airline_api.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    PassengerService passengerService;

    @Autowired
    FlightService flightService;


    public Booking addNewBooking(BookingDTO bookingDTO){
        Passenger passenger = passengerService.getSinglePassenger(bookingDTO.getPassengerId()).get();
        Flight flight = flightService.getSingleFlight(bookingDTO.getFlightId()).get();
        Booking booking = new Booking(flight, passenger, bookingDTO.getSeatNumber());
        flightService.reduceFlightCapacity(bookingDTO.getFlightId());
        bookingRepository.save(booking);
        return booking;
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Optional<Booking> getSingleBooking(Long id){
        return bookingRepository.findById(id);
    }

    public Booking updateMealPreference(Long id, String mealPreference){
        Booking booking = getSingleBooking(id).get();
        booking.setMealPreference(mealPreference);
        bookingRepository.save(booking);
        return booking;
    }

}
