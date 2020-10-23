package dao;

import model.User;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDao {
    Connection connection;

    PreparedStatement insertQuery;
    PreparedStatement deleteQuery;
    PreparedStatement selectQuery;
    PreparedStatement selectExactQuery;
    PreparedStatement getSelectQuery;
    PreparedStatement updateQueryUsername, updateQueryEmail, updateQueryPassword, updateQueryLogin;

    public UserDao(Connection connection) {
        this.connection = connection;
        try {
            insertQuery = connection.prepareStatement("INSERT INTO user VALUES (null, ?, ?, ?, ?)");
            deleteQuery = connection.prepareStatement("DELETE FROM user WHERE username = ?");
            selectQuery = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            selectExactQuery = connection.prepareStatement("SELECT * FROM user WHERE BINARY username = ?");
            getSelectQuery = connection.prepareStatement("SELECT * FROM user WHERE email = ?");
            updateQueryUsername = connection.prepareStatement("UPDATE user SET username = ? WHERE username = ?");
            updateQueryEmail = connection.prepareStatement("UPDATE user SET email = ? WHERE email = ?");
            updateQueryPassword = connection.prepareStatement("UPDATE user SET password = ? WHERE password = ?");
            updateQueryLogin = connection.prepareStatement("UPDATE user SET loginTime = ? WHERE id = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insert(User user) {
        try {
            insertQuery.setString(1, user.getUsername());
            insertQuery.setString(2, user.getPassword());
            insertQuery.setString(3, user.getEmail());
            insertQuery.setTime(4, Time.valueOf(user.getLoginTime()));
            return insertQuery.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> findByExactUsername(String username) {
        try {
            selectExactQuery.setString(1, username);
            ResultSet result = selectExactQuery.executeQuery();

            List<User> users = new ArrayList<>();

            while(result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getTime("loginTime").toLocalTime()
                );
                users.add(user);
            }
            return  users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<User> findByUsername(String username) {
        try {
            System.out.println(username);
            selectQuery.setString(1, username);
            ResultSet result = selectQuery.executeQuery();

            List<User> users = new ArrayList<>();

            while(result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getTime("loginTime").toLocalTime()
                );
                users.add(user);
            }
            return  users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<User> findByEmail(String email) {
        try {
            getSelectQuery.setString(1, email);
            ResultSet result = getSelectQuery.executeQuery();

            List<User> users = new ArrayList<>();

            while(result.next()) {
                User user = new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getTime("loginTime").toLocalTime()
                );
                users.add(user);
            }
            return  users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public String checkPassword(String username) {
        try {
            selectQuery.setString(1, username);
            ResultSet result = selectQuery.executeQuery();
            User user = null;
                while (result.next()) {
                    user = new User(
                            result.getInt("id"),
                            result.getString("username"),
                            result.getString("password"),
                            result.getString("email"),
                            result.getTime("loginTime").toLocalTime()
                    );
                }
            if(user != null)
                return  user.getPassword();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void updateUser(String newUsername, String username) {
        try {
            updateQueryUsername.setString(1, newUsername);
            updateQueryUsername.setString(2, username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            updateQueryUsername.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmail(String newEmail, String email) {
        try {
            updateQueryEmail.setString(1, newEmail);
            updateQueryEmail.setString(2, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            updateQueryEmail.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePassword(String newPassword, String password) {
        try {
            updateQueryPassword.setString(1, newPassword);
            updateQueryPassword.setString(2, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            updateQueryPassword.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLoginTime(LocalTime newLoginTime, int id) {
        try {
            updateQueryLogin.setTime(1, Time.valueOf(newLoginTime));
            updateQueryLogin.setInt(2, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            updateQueryLogin.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initUser(String username) {
        try {
            getSelectQuery.setString(1, username);
            ResultSet result = getSelectQuery.executeQuery();
            User user = User.getInstance();

            while(result.next()) {
                user.setId(result.getInt("id"));
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

