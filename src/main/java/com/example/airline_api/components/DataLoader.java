package com.example.airline_api.components;

import com.example.airline_api.models.Booking;
import com.example.airline_api.models.Flight;
import com.example.airline_api.models.MealPreference;
import com.example.airline_api.models.Passenger;
import com.example.airline_api.repositories.BookingRepository;
import com.example.airline_api.repositories.FlightRepository;
import com.example.airline_api.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

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
        Flight londonFlight = new Flight("London Heathrow Airport", 5, LocalDate.of(2024,6, 5), LocalTime.of(18,30));
        flightRepository.save(londonFlight);

        Flight dublinFlight = new Flight("Dublin Airport", 5, LocalDate.of(2024, 7, 6),LocalTime.of(18,30));
        flightRepository.save(dublinFlight);

        Passenger rabin = new Passenger("Rabin Kandel", "kandelrabin@live.com");
        passengerRepository.save(rabin);

        Passenger john = new Passenger("John Doe", "johndoe@gmail.com");
        passengerRepository.save(john);

        Passenger harry = new Passenger("Harry Maguire", "hmguire@outlook.com");
        passengerRepository.save(harry);

        Passenger jude = new Passenger("Jude Bellingham", "jbell@gmail.com");
        passengerRepository.save(jude);

        Passenger salah = new Passenger("Mohamed Salah", "msalah@outlook.com");
        passengerRepository.save(salah);

        Booking booking1 = new Booking(londonFlight, rabin,1, MealPreference.valueOf("SDML"));
        londonFlight.setCapacity(londonFlight.getCapacity()-1);
        flightRepository.save(londonFlight);
        bookingRepository.save(booking1);

        Booking booking2 = new Booking(dublinFlight, john,1, MealPreference.valueOf("VGML"));
        dublinFlight.setCapacity(dublinFlight.getCapacity()-1);
        flightRepository.save(dublinFlight);
        bookingRepository.save(booking2);

    }
}
