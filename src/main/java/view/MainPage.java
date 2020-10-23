package view;

import controller.ActionsController;
import controller.FlightController;
import model.Flight;
import model.User;
import myUtility.MyUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class MainPage extends JFrame {
    private JPanel panel, innerPanel;
    private JLabel clockLabel;
    private JButton addFlightButton, myAccountButton;
    private EmptyBorder border = new EmptyBorder(0,10,0,0);
    User user = User.getInstance();

    public MainPage() {

        initDefaultProperties();
        panel = new JPanel(new GridLayout(4, 1));
        innerPanel = new JPanel(new GridLayout(1,2));

        initClock();
        initTable();
        initAddFlightButton();
        initMyAccountButton();

        add(panel, BorderLayout.CENTER);
        setVisible(true);
        }

        public void initDefaultProperties() {
            MyUtility.initMenu(this);
            setTitle("Main Page");
            setSize(500,500);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void initClock() {
            clockLabel = new JLabel("clock");
            clockLabel.setBorder(border);

            clockLabel.setText(DateFormat.getDateTimeInstance().format(new Date()));
            add(clockLabel);
            Timer timer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clockLabel.setText(DateFormat.getDateTimeInstance().format(new Date()));
                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
            timer.setInitialDelay(0);
            timer.start();

            innerPanel.add(clockLabel);

            panel.add(innerPanel);
        }

        public void initTable() {
            User user = User.getInstance();
            FlightController flightController = new FlightController();

            java.util.List<Flight> flights = flightController.findFlight(user.getId());
            ArrayList<Flight> flightsList = new ArrayList<>(flights);

            Object[] columns = {"Sursa","Destinatie","Ora plecare","Ora sosire", "Zile", "Pret"};
            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

            for (int i = 0; i < flightsList.size(); i++){
                String origin = flightsList.get(i).getOrigin();
                String destination = flightsList.get(i).getDestination();
                LocalTime departureTime = flightsList.get(i).getDepartureTime();
                LocalTime arrivalTime = flightsList.get(i).getArrivalTime();
                String days = flightsList.get(i).getDays();
                int price = flightsList.get(i).getPrice();



                Object[] data = {origin, destination, departureTime, arrivalTime, days, price};

                tableModel.addRow(data);

            }

            JTable jTable = new JTable(tableModel);
            jTable.setBounds(30,40,200,300);
            JScrollPane sp = new JScrollPane(jTable);

            panel.add(sp);
        }

        private void initAddFlightButton() {
            addFlightButton = new JButton("Adauga zbor");
            addFlightButton.setSize(500, 30);
            addFlightButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(Duration.between(user.getLoginTime(), LocalTime.now()).toMinutes() >= 15) {
                        dispose();
                        LoginPage loginPage = new LoginPage();
                        loginPage.setVisible(true);
                        ActionsController actionsController = new ActionsController();
                        actionsController.addAction(MyUtility.initAction("accessed Login Page"));
                    } else {
                        dispose();
                        AddFlight addFlight = new AddFlight();
                        addFlight.setVisible(true);
                        ActionsController actionsController = new ActionsController();
                        actionsController.addAction(MyUtility.initAction("accessed Add Flight Page"));
                    }
                }
            });
            panel.add(addFlightButton);
        }

        private void initMyAccountButton() {
            myAccountButton = new JButton("Contul meu");
            myAccountButton.setSize(500,30);
            myAccountButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    if(Duration.between(user.getLoginTime(), LocalTime.now()).toMinutes() >= 15) {
                        dispose();
                        LoginPage loginPage = new LoginPage();
                        loginPage.setVisible(true);
                        ActionsController actionsController = new ActionsController();
                        actionsController.addAction(MyUtility.initAction("accessed Login Page"));
                    } else {
                        dispose();
                        MyAccount myAccount = new MyAccount();
                        myAccount.setVisible(true);
                        ActionsController actionsController = new ActionsController();
                        actionsController.addAction(MyUtility.initAction("accessed MyAccount Page"));
                    }
                }
            });

            panel.add(myAccountButton);
        }


}
