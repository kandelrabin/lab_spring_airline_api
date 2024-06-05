package com.example.airline_api.controllers;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.BookingDTO;
import com.example.airline_api.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingService bookingService;

    // INDEX
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings(){
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Booking> addNewBooking(@RequestBody BookingDTO bookingDTO){
        Booking booking = bookingService.addNewBooking(bookingDTO);
        if (Objects.isNull(booking)) {
            return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
        }
        else {
            return new ResponseEntity<>(booking, HttpStatus.OK);
        }
    }


    // PARTIAL UPDATE
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Booking> updateMealPreference(@PathVariable Long id, @RequestBody Map<String,String> update){
        String mealPreference = update.get("mealPreference");
        Optional<Booking> bookingOptional = bookingService.getSingleBooking(id);
        if (bookingOptional.isPresent()){
            bookingService.updateMealPreference(id, mealPreference);
            return new ResponseEntity<>(bookingOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // SHOW
    @GetMapping(value = "/{id}")
    public ResponseEntity<Booking> getSingleBooking(@PathVariable Long id){
        Optional<Booking> bookingOptional = bookingService.getSingleBooking(id);
        if (bookingOptional.isPresent()) {
            return new ResponseEntity<>(bookingOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
