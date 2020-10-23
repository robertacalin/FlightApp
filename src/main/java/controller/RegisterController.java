package controller;

import dao.FlightDao;
import dao.UserDao;
import model.User;

import java.sql.Connection;

public class RegisterController {

    private UserDao userDao;
    private FlightDao flightDao;

    public RegisterController() {
        Connection connection = DatabaseConnection.getConnection();
        this.userDao = new UserDao(connection);
        this.flightDao = new FlightDao(connection);
    }


    public static boolean isValidPassword(String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;

        if (password.length() <= 6)
            return false;

        for(int i = 0; i < password.length(); i ++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                numberFlag = true;
            } else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag)
                return true;
        }


        return false;
    }

    public static boolean isConfirmed(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);

        return m.matches();
    }

    public boolean isAlreadyUsed(String username) {
        return !this.userDao.findByUsername(username).isEmpty();
    }

    public void addUser(User user) {
        this.userDao.insert(user);
    }
}
