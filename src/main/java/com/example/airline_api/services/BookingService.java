package com.example.airline_api.services;

import com.example.airline_api.models.*;
import com.example.airline_api.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        int capacity = flight.getCapacity();
        if (capacity > 0){
            Passenger passenger = passengerService.getSinglePassenger(bookingDTO.getPassengerId()).get();
            MealPreference mealPreference = MealPreference.valueOf(bookingDTO.getMealPreference());
            int totalSeatsOnFlight = flight.getTotalSeatsOnFlight();
            int seatNumber = getRandomWithExclusion(totalSeatsOnFlight, flight.getAllocatedSeats());

            flightService.allocateSeat(bookingDTO.getFlightId(), seatNumber);

            Booking booking = new Booking(flight, passenger,seatNumber,  mealPreference);

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

    static int getRandomWithExclusion(int max, List<Integer> exclude) {
        Collections.sort(exclude);
        int random = 1 + (int) ((max - 1 + 1 - exclude.size()) * Math.random());
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }


}
