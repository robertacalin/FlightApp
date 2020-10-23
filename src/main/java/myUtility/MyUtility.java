package myUtility;

import controller.ActionsController;
import model.Actions;
import view.History;
import view.LoginPage;
import view.MainPage;
import view.MyAccount;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class MyUtility {

    public MyUtility() {

    }

    public static void errorHandler(String message) {
        JPanel panel = new JPanel();
        JOptionPane.showMessageDialog(panel,
                message,
                "Eroare",
                JOptionPane.ERROR_MESSAGE);
    }

    public static Actions initAction(String actionName) {
        Actions action = new Actions();

        action.setAction(actionName);
        action.setTime(LocalDateTime.now().toString());

        return action;
    }

    public static void initMenu(JFrame frame) {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem homeItem, accountItem, logOutItem, historyItem;

        menuBar = new JMenuBar();
        menu = new JMenu("Options");
        homeItem = new JMenuItem("home");
        accountItem = new JMenuItem("account");
        logOutItem = new JMenuItem("Log Out");
        historyItem = new JMenuItem("history");

        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                MainPage mainPage = new MainPage();
                mainPage.setVisible(true);
                ActionsController actionsController = new ActionsController();
                actionsController.addAction(MyUtility.initAction("accessed Main Page"));
            }
        });

        accountItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                MyAccount myAccount = new MyAccount();
                myAccount.setVisible(true);
                ActionsController actionsController = new ActionsController();
                actionsController.addAction(MyUtility.initAction("accessed MyAccount Page"));
            }
        });

        logOutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                frame.dispose();
                ActionsController actionsController = new ActionsController();
                actionsController.addAction(MyUtility.initAction("logged out"));
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                actionsController.addAction(MyUtility.initAction("accessed Login Page"));
            }
        });

        historyItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ActionsController actionsController = new ActionsController();
                actionsController.addAction(MyUtility.initAction("accessed History Page"));
                History history = new History();
                history.setVisible(true);
            }
        });

        menu.add(homeItem);
        menu.add(accountItem);
        menu.add(logOutItem);
        menu.add(historyItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

    }
}
