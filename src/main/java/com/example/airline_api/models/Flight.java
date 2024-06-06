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
    private List<Integer> seatsAvailable;

    public Flight(String destination, int capacity, LocalDate departureDate, LocalTime departureTime) {
        this.destination = destination;
        this.capacity = capacity;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.bookings = new ArrayList<>();
        this.seatsAvailable = seatsOnEmptyFlight(capacity);
    }

    public Flight() {
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

//  Additional getter and setters for flight


    public List<Integer> getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(List<Integer> seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public int allocateRandomSeat(){
        Random rand = new Random();
        int randomIndex = rand.nextInt(this.seatsAvailable.size());
        int randomSeat = this.seatsAvailable.get(randomIndex);
        this.seatsAvailable.remove(randomSeat);
        return randomSeat;
    }

    public void returnSeat(int seatNumber) throws Exception{
        if (!this.seatsAvailable.contains(seatNumber)){
            this.seatsAvailable.add(seatNumber);
        } else {
            throw new Exception("Cannot return unallocated seat!");
        }
    }

    public ArrayList<Integer> seatsOnEmptyFlight(int totalCapacity){
        ArrayList<Integer> allSeats = new ArrayList<>();
        for (int i=0; i < totalCapacity-1; i++){
            allSeats.add(i+1);
        }
        return allSeats;
    }

}
