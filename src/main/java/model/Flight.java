package model;

import java.time.LocalTime;
import java.util.Objects;

public class Flight {
    private int id;
    private String origin, destination;
    private LocalTime departureTime, arrivalTime;
    private LocalTime duration;
    private String days;
    int price;

    public Flight() {
    }

    public Flight(int id, String origin, String destination, LocalTime departureTime, LocalTime duration, LocalTime arrivalTime, String days, int price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.duration = duration;
        this.arrivalTime = arrivalTime;
        this.days = days;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime departureTime, LocalTime duration) {
        LocalTime aux = departureTime.plusMinutes(duration.getMinute());
        this.arrivalTime = aux.plusHours(duration.getHour());
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;
        Flight flight = (Flight) o;
        return getId() == flight.getId() &&
                Float.compare(flight.getPrice(), getPrice()) == 0 &&
                Objects.equals(getOrigin(), flight.getOrigin()) &&
                Objects.equals(getDestination(), flight.getDestination()) &&
                Objects.equals(getDepartureTime(), flight.getDepartureTime()) &&
                Objects.equals(getDuration(), flight.getDuration()) &&
                Objects.equals(getArrivalTime(), flight.getArrivalTime()) &&
                Objects.equals(getDays(), flight.getDays());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOrigin(), getDestination(), getDepartureTime(), getDuration(), getArrivalTime(), getDays(), getPrice());
    }
}
