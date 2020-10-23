package model;
import java.util.Date;
import java.util.Objects;

public class Actions {
    int id;
    String action, time;

    public Actions() {}

    public Actions(int id, String action, String time) {
        this.id = id;
        this.action = action;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actions)) return false;
        Actions actions = (Actions) o;
        return getId() == actions.getId() &&
                Objects.equals(getAction(), actions.getAction()) &&
                Objects.equals(getTime(), actions.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAction(), getTime());
    }

    @Override
    public String toString() {
        return "Actions{" +
                "id=" + id +
                ", action='" + action + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
