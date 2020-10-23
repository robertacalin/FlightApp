package controller;

import dao.UserDao;

import java.sql.Connection;
import java.time.LocalTime;

public class UserController {

    private UserDao userDao;

    public UserController() {
        Connection connection = DatabaseConnection.getConnection();
        this.userDao = new UserDao(connection);
    }

    public boolean isGoodPassword(String username, String password) {
        if (!emailExists(username))
            return this.userDao.checkPassword(username).equals(password);
        String username1 = this.userDao.findByEmail(username).get(0).getUsername();
        return this.userDao.checkPassword(username1).equals(password);
    }

    public int getIdByEmail(String email) {
        return this.userDao.findByEmail(email).get(0).getId();
    }

    public boolean emailExists(String email) {
        return !this.userDao.findByEmail(email).isEmpty();
    }

    public boolean isAlreadyExactlyUsed(String username) {
        return !this.userDao.findByExactUsername(username).isEmpty();
    }

    public void newUsername(String newUsername, String username) {
        this.userDao.updateUser(newUsername, username);
    }

    public void newEmail(String newEmail, String email) {
        this.userDao.updateEmail(newEmail, email);
    }

    public void newPassword(String newPassword, String password) {
        this.userDao.updatePassword(newPassword, password);
    }

    public void newLoginTime(LocalTime newLoginTime, int id) {
        this.userDao.updateLoginTime(newLoginTime, id);
    }

    public void myUser(String username) {
        this.userDao.initUser(username);
    }
}
