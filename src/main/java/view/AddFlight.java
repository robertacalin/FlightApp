package view;

import controller.ActionsController;
import controller.FlightController;
import model.Flight;
import model.User;
import myUtility.MyUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddFlight extends JFrame {
    private JPanel panel;
    private JLabel originLabel, destinationLabel, departureTimeLabel, durationLabel, priceLabel, dayLabel;
    private JLabel mondayLabel, tuesdayLabel, wednesdayLabel, thursdayLabel, fridayLabel, saturdayLabel, sundayLabel;
    private JTextField originField, destinationField, priceField;
    private JComboBox departureTimeHourBox, departureTimeMinutesBox, durationHourBox, durationMinutesBox;
    private Checkbox mondayCheckBox, tuesdayCheckBox, wednesdayCheckBox, thursdayCheckBox, fridayCheckBox, saturdayCheckBox, sundayCheckBox;
    private JButton cancelButton, addFlightButton;
    private EmptyBorder border = new EmptyBorder(0,10,0,0);

    public AddFlight() {
        initDefaultProperties();
        panel = new JPanel(new GridLayout(7,2));

        initOrigin();
        initDestination();
        initDay();
        initDepartureTime();
        initDuration();
        initPrice();
        initCancelButton();
        initAddFlightButton();

        add(panel);
        setVisible(true);
    }

    private void initDefaultProperties() {
        MyUtility.initMenu(this);
        setTitle("Add flight");
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initOrigin() {
        originLabel = new JLabel("Sursa");
        originLabel.setBorder(border);
        panel.add(originLabel);

        originField = new JTextField();
        panel.add(originField);
    }

    private void initDestination() {
        destinationLabel = new JLabel("Destinatie");
        destinationLabel.setBorder(border);
        panel.add(destinationLabel);

        destinationField = new JTextField();
        panel.add(destinationField);
    }

    private JComboBox createHoursComboBox() {
        return createTimeComboBox(24);
    }

    private JComboBox createMinutesComboBox() {
        return createTimeComboBox(60);
    }

    private JComboBox createTimeComboBox(int max) {
        final JComboBox cbox = new JComboBox<>();
        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < max; i++) {
            String iString;
            if (i < 10) {
                iString = "0" + i;
            } else {
                iString = Integer.toString(i);
            }
            map.put(i, iString);
        }

        for (Integer id : map.keySet()) {
            cbox.addItem(map.get(id));
        }

        return cbox;
    }

    private void initDepartureTime() {
        JPanel innerPanel = new JPanel(new GridLayout(1,2));

        departureTimeLabel = new JLabel("Ora plecare");
        departureTimeLabel.setBorder(border);
        panel.add(departureTimeLabel);


        departureTimeHourBox = createHoursComboBox();
        innerPanel.add(departureTimeHourBox);


        departureTimeMinutesBox = createMinutesComboBox();
        innerPanel.add(departureTimeMinutesBox);

        panel.add(innerPanel);
    }

    private void initDuration() {
        JPanel innerPanel = new JPanel(new GridLayout(1,2));

        durationLabel = new JLabel("Durata zborului");
        durationLabel.setBorder(border);
        panel.add(durationLabel);

        durationHourBox = createHoursComboBox();
        innerPanel.add(durationHourBox);

        durationMinutesBox = createMinutesComboBox();
        innerPanel.add(durationMinutesBox);

        panel.add(innerPanel);
    }

    private void initDay() {
        JPanel innerPanel = new JPanel(new GridLayout(2,7));
        dayLabel = new JLabel("Ziua");
        dayLabel.setBorder(border);
        panel.add(dayLabel);

        mondayLabel = new JLabel("M");
        tuesdayLabel = new JLabel("T");
        wednesdayLabel = new JLabel("W");
        thursdayLabel = new JLabel("T");
        fridayLabel = new JLabel("F");
        saturdayLabel = new JLabel("S");
        sundayLabel = new JLabel("S");

        innerPanel.add(mondayLabel);
        innerPanel.add(tuesdayLabel);
        innerPanel.add(wednesdayLabel);
        innerPanel.add(thursdayLabel);
        innerPanel.add(fridayLabel);
        innerPanel.add(saturdayLabel);
        innerPanel.add(sundayLabel);

        mondayCheckBox = new Checkbox();
        tuesdayCheckBox = new Checkbox();
        wednesdayCheckBox = new Checkbox();
        thursdayCheckBox = new Checkbox();
        fridayCheckBox = new Checkbox();
        saturdayCheckBox = new Checkbox();
        sundayCheckBox = new Checkbox();

        innerPanel.add(mondayCheckBox);
        innerPanel.add(tuesdayCheckBox);
        innerPanel.add(wednesdayCheckBox);
        innerPanel.add(thursdayCheckBox);
        innerPanel.add(fridayCheckBox);
        innerPanel.add(saturdayCheckBox);
        innerPanel.add(sundayCheckBox);

        panel.add(innerPanel);
    }

    private void initPrice() {
        priceLabel = new JLabel("Pret");
        priceLabel.setBorder(border);
        panel.add(priceLabel);

        priceField = new JTextField();
        priceField.setText("0");
        panel.add(priceField);
    }

    private void initCancelButton() {
        User user = User.getInstance();
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Duration.between(user.getLoginTime(), LocalTime.now()).toMinutes() >= 15) {
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                } else {
                    dispose();
                    MainPage mainPage = new MainPage();
                    mainPage.setVisible(true);
                }
            }
        });
        panel.add(cancelButton);
    }

    private void initAddFlightButton() {
        addFlightButton = new JButton("Adauga zbor");
        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = User.getInstance();
                FlightController flightController = new FlightController();
                Flight flight = initFlight();

                if(Duration.between(user.getLoginTime(), LocalTime.now()).toMinutes() >= 15) {
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                } else {
                if(!FlightController.isValidOrigin(originField.getText())) {
                    MyUtility.errorHandler("Sursa trebuie sa aiba minim 3 litere!");
                } else if(!FlightController.isValidDestination(destinationField.getText())) {
                    MyUtility.errorHandler("Destinatia trebuie sa aiba minim 3 litere!");
                } else if(!FlightController.isDifferent(originField.getText(),destinationField.getText())) {
                    MyUtility.errorHandler("Destinatia trebuie sa fie diferita de sursa!");
                } else if(!flightController.areValidCoordinates(user.getId(), originField.getText(), destinationField.getText())) {
                    MyUtility.errorHandler("Zborul deja exista!");
                }
                else {
                    try {
                        int priceInteger = Integer.parseInt(priceField.getText());
                        if (!FlightController.isValidPrice(priceInteger)) {
                            MyUtility.errorHandler("Pretul trebuie sa aiba o valoare pozitiva");
                        } else {
                            initFlight();
                            flightController.addFlight(flight);
                            flightController.addMyFlight(flight, user);

                            ActionsController actionsController = new ActionsController();
                            actionsController.addAction(MyUtility.initAction("added flight"));
                            dispose();
                            MainPage mainPage = new MainPage();
                            mainPage.setVisible(true);
                            actionsController.addAction(MyUtility.initAction("accessed Main Page"));
                        }
                    } catch (NumberFormatException err) {
                        MyUtility.errorHandler("Pretul trebuie sa fie un scalar!");
                    }
                }

                }
            }
        });

        panel.add(addFlightButton);
    }

    private Flight initFlight() {
        Flight flight = new Flight();
        ArrayList<String> names = new ArrayList<>();
        String hoursDeparture, minutesDeparture, hoursDuration, minutesDuration;

        if(mondayCheckBox.getState()) { names.add("Luni"); }
        if(tuesdayCheckBox.getState()) { names.add("Marti"); }
        if(wednesdayCheckBox.getState()) { names.add("Miercuri"); }
        if(thursdayCheckBox.getState()) { names.add("Joi"); }
        if(fridayCheckBox.getState()) { names.add("Vineri"); }
        if(saturdayCheckBox.getState()) { names.add("Sambata"); }
        if(sundayCheckBox.getState()) { names.add("Duminica"); }

        if (names.size() == 0)
            MyUtility.errorHandler("Selectati ziua!");

        String name = names.toString();

        hoursDeparture = departureTimeHourBox.getSelectedItem().toString();
        minutesDeparture = departureTimeMinutesBox.getSelectedItem().toString();
        hoursDuration = durationHourBox.getSelectedItem().toString();
        minutesDuration = durationMinutesBox.getSelectedItem().toString();

        flight.setOrigin(originField.getText());
        flight.setDestination(destinationField.getText());
        flight.setDepartureTime(LocalTime.parse(hoursDeparture + ":" + minutesDeparture));
        flight.setDuration(LocalTime.parse(hoursDuration + ":" + minutesDuration));
        flight.setArrivalTime(flight.getDepartureTime(), flight.getDuration());
        flight.setDays(name);
        flight.setPrice(Integer.parseInt(priceField.getText()));

        return flight;
    }

}
