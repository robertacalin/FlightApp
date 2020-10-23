package dao;

import model.Flight;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightDao {
    Connection connection;

    PreparedStatement insertQuery, insertQuery2;
    PreparedStatement selectQuery, selectFlightQuery;

    public FlightDao(Connection connection) {
        this.connection = connection;
        try {
            insertQuery = connection.prepareStatement("INSERT INTO zboruri VALUES (null, ?, ?, ?, ?, ?, ?)");
            insertQuery2 = connection.prepareStatement("INSERT INTO account VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)");
            selectQuery = connection.prepareStatement("SELECT * FROM account WHERE user_id = ?");
            selectFlightQuery = connection.prepareStatement("SELECT * FROM account WHERE user_id = ? && sursa = ? && destinatie = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertFlight(Flight flight) {
        try {
            insertQuery.setString(1, flight.getOrigin());
            insertQuery.setString(2, flight.getDestination());
            insertQuery.setTime(3, Time.valueOf(flight.getDepartureTime()));
            insertQuery.setTime(4, Time.valueOf(flight.getArrivalTime()));
            insertQuery.setString(5, flight.getDays());
            insertQuery.setInt(6, flight.getPrice());
            return insertQuery.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertMyFlight(Flight flight, User user) {
        try {
            insertQuery2.setString(1, flight.getOrigin());
            insertQuery2.setString(2, flight.getDestination());
            insertQuery2.setTime(3, Time.valueOf(flight.getDepartureTime()));
            insertQuery2.setTime(4, Time.valueOf(flight.getDuration()));
            insertQuery2.setTime(5, Time.valueOf(flight.getArrivalTime()));
            insertQuery2.setString(6, flight.getDays());
            insertQuery2.setInt(7, flight.getPrice());
            insertQuery2.setInt(8, user.getId());

            return insertQuery2.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Flight> findByUserId(int id) {
        try {
            selectQuery.setInt(1, id);
            ResultSet result = selectQuery.executeQuery();

            List<Flight> flights = new ArrayList<>();

            while(result.next()) {
                Flight flight = new Flight(
                        result.getInt("id"),
                        result.getString("sursa"),
                        result.getString("destinatie"),
                        result.getTime("ora_plecare").toLocalTime(),
                        result.getTime("durata").toLocalTime(),
                        result.getTime("ora_sosire").toLocalTime(),
                        result.getString("zile"),
                        result.getInt("pret")
                );
                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Flight> findByUserIdAndCoordinates(int id, String source, String destination) {
        try {
            selectFlightQuery.setInt(1, id);
            selectFlightQuery.setString(2, source);
            selectFlightQuery.setString(3, destination);

            ResultSet result = selectFlightQuery.executeQuery();

            List<Flight> flights = new ArrayList<>();

            while(result.next()) {
                Flight flight = new Flight(
                        result.getInt("id"),
                        result.getString("sursa"),
                        result.getString("destinatie"),
                        result.getTime("ora_plecare").toLocalTime(),
                        result.getTime("durata").toLocalTime(),
                        result.getTime("ora_sosire").toLocalTime(),
                        result.getString("zile"),
                        result.getInt("pret")
                );
                flights.add(flight);
            }
            return flights;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
