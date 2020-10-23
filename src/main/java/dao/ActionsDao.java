package dao;

import model.Actions;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionsDao {
    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement selectQuery;

    public ActionsDao(Connection connection) {
        this.connection = connection;
        try {
            insertQuery = connection.prepareStatement("INSERT INTO actions VALUES (null, ?, ?, ?)");
            selectQuery = connection.prepareStatement("SELECT * FROM actions WHERE user_id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertAction(Actions actions) {
        User user = User.getInstance();

        try {
            insertQuery.setString(1, actions.getAction());
            insertQuery.setString(2, actions.getTime());
            insertQuery.setInt(3, user.getId());
            return insertQuery.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertRegisterAction(Actions actions, int userId) {
        try {
            insertQuery.setString(1, actions.getAction());
            insertQuery.setString(2, actions.getTime());
            insertQuery.setInt(3, userId);
            return insertQuery.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Actions> findByUser(int id) {
        try {
            selectQuery.setInt(1, id);
            ResultSet result = selectQuery.executeQuery();

            List<Actions> actions = new ArrayList<>();

            while(result.next()) {
                Actions action = new Actions(
                        result.getInt("id"),
                        result.getString("action"),
                        result.getString("time")
                );
                actions.add(action);
            }
            return actions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
