package com.example.airline_api.models;


public class BookingDTO {

    private Long flightId;
    private Long passengerId;
    private String mealPreference;

    public BookingDTO(Long flightId, Long passengerId, String mealPreference) {
        this.flightId = flightId;
        this.passengerId = passengerId;
        this.mealPreference = mealPreference;
    }

    public BookingDTO() {
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }


    public String getMealPreference() {
        return mealPreference;
    }

    public void setMealPreference(String mealPreference) {
        this.mealPreference = mealPreference;
    }
}
