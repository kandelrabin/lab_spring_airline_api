package com.example.airline_api.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "destination")
    private String destination;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @OneToMany(mappedBy = "flight")
    @JsonIgnoreProperties({"flight"})
    private List<Booking> bookings;

    @JsonIgnore
    private List<Integer> allocatedSeats;

    @JsonIgnore
    private final int totalSeatsOnFlight;


    public Flight(String destination, int capacity, LocalDate departureDate, LocalTime departureTime) {
        this.destination = destination;
        this.capacity = capacity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.bookings = new ArrayList<>();
        this.allocatedSeats = new ArrayList<>();
        this.totalSeatsOnFlight = capacity;
    }

    public Flight() {
        this.totalSeatsOnFlight = 0;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public void addBooking(Booking booking){
        this.bookings.add(booking);
    }

    public void removeBooking(Booking booking){
        this.bookings.remove(booking);
    }

    public List<Integer> getAllocatedSeats() {
        return this.allocatedSeats;
    }

    public void setAllocatedSeats(List<Integer> allocatedSeats) {
        this.allocatedSeats = allocatedSeats;
    }

    public void addToAllocatedSeats(int seatNumber){
        this.allocatedSeats.add(seatNumber);
    }

    public void removeFromAllocatedSeats(int seatNumber){
        this.allocatedSeats.remove(seatNumber);
    }

    public int getTotalSeatsOnFlight() {
        return totalSeatsOnFlight;
    }
}
