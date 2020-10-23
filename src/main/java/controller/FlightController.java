package controller;

import dao.FlightDao;
import model.Flight;
import model.User;

import java.sql.Connection;
import java.util.List;

public class FlightController {
    private FlightDao flightDao;

    public FlightController() {
        Connection connection = DatabaseConnection.getConnection();
        flightDao = new FlightDao(connection);
    }

    public static boolean isValidOrigin(String origin) {
        return (origin.length() > 3);
    }

    public static boolean isValidDestination(String destination) {
        return (destination.length() > 3);
    }

    public static boolean isDifferent(String origin, String destination) {
        return (!origin.equals(destination));
    }

    public static boolean isValidPrice(int price) {
        return (price > 0);
    }

    public void addFlight(Flight flight) {
        this.flightDao.insertFlight(flight);
    }

    public void addMyFlight(Flight flight, User user) {
        this.flightDao.insertMyFlight(flight, user);
    }

    public List<Flight> findFlight(int id) {
        return this.flightDao.findByUserId(id);
    }

    public boolean areValidCoordinates(int id, String source, String destination) {
        if(this.flightDao.findByUserIdAndCoordinates(id, source, destination).isEmpty())
            return true;
        return false;
    }
}
