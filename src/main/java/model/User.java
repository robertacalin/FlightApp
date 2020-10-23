package model;

import java.time.LocalTime;
import java.util.Objects;

public class User {
    private static int id;
    private static String username, password, email;
    private static User instance;
    private static LocalTime loginTime;

    private User() {
    }

    public User(int id, String username, String password, String email, LocalTime loginTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.loginTime = loginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalTime loginTime) {
        User.loginTime = loginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getLoginTime(), user.getLoginTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmail(), getLoginTime());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }

    public static User getInstance(){
        if(instance == null) {
            synchronized (User.class) {
                if(instance == null) {
                    instance = new User(id, username, password, email, loginTime);
                }
            }
        }
        return instance;
    }
}

