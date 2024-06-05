package com.example.airline_api.components;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import com.example.airline_api.services.FlightService;
import com.example.airline_api.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Flight londonFlight = new Flight("London Heathrow Airport", 50, "05/06/2024", "18:30");
        flightRepository.save(londonFlight);

        Flight dublinFlight = new Flight("Dublin Airport", 50, "06/07/2024", "18:30");
        flightRepository.save(dublinFlight);

        Passenger rabin = new Passenger("Rabin Kandel", "kandelrabin@live.com");
        passengerRepository.save(rabin);

        Passenger john = new Passenger("John Doe", "johndoe@gmail.com");
        passengerRepository.save(john);

        Passenger harry = new Passenger("Harry Maguire", "hmguire@outlook.com");
        passengerRepository.save(harry);

        Booking booking1 = new Booking(londonFlight, rabin, 1);
        londonFlight.setCapacity(londonFlight.getCapacity()-1);
        flightRepository.save(londonFlight);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking(dublinFlight, john, 1);
        dublinFlight.setCapacity(dublinFlight.getCapacity()-1);
        flightRepository.save(dublinFlight);
        bookingRepository.save(booking2);

    }
}
