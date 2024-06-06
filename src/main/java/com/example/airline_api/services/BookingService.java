package com.example.airline_api.services;

import com.example.airline_api.models.*;
import com.example.airline_api.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Flight flight = flightService.getSingleFlight(bookingDTO.getFlightId()).get();
        if (flight.getCapacity() > 0){
            Passenger passenger = passengerService.getSinglePassenger(bookingDTO.getPassengerId()).get();
            MealPreference mealPreference = MealPreference.valueOf(bookingDTO.getMealPreference());
            int seatNumber = flightService.allocateSeat(bookingDTO.getFlightId());
            Booking booking = new Booking(flight, passenger,seatNumber,  mealPreference);
            flightService.reduceFlightCapacity(bookingDTO.getFlightId());
            bookingRepository.save(booking);
            return booking;
        } else {
            return null;
        }
    }

    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public Optional<Booking> getSingleBooking(Long id){
        return bookingRepository.findById(id);
    }

    public Booking updateMealPreference(Long id, MealPreference mealPreference){
        Booking booking = getSingleBooking(id).get();
        booking.setMealPreference(mealPreference);
        bookingRepository.save(booking);
        return booking;
    }


}
