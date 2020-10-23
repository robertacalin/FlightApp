package view;

import controller.ActionsController;
import controller.RegisterController;
import controller.UserController;
import model.User;
import myUtility.MyUtility;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

public class LoginPage extends JFrame{
    private JPanel panel;

    private JButton loginButton, cancelButton;
    private JLabel usernameLabel, passwordLabel;

    private JTextField usernameField;
    private JPasswordField passwordField;

    EmptyBorder border = new EmptyBorder(0, 10, 0, 0);

    public LoginPage() {
        initDefaultProperties();
        panel = new JPanel(new GridLayout(3, 2));

        initUsername();
        initPassword();
        initLoginButton();
        initCancelButton();

        add(panel, BorderLayout.CENTER);
        setVisible(true);
        }

        private void initDefaultProperties() {
        setSize(300, 200);
        setLocationRelativeTo(null);
        setTitle("Login");
        }

        private void initUsername() {
            usernameLabel = new JLabel("Username/e-mail:");
            usernameLabel.setBorder(border);
            panel.add(usernameLabel);

            usernameField = new JTextField();
            panel.add(usernameField);

        }

        private void initPassword() {
            passwordLabel = new JLabel("Password");
            passwordLabel.setBorder(border);
            panel.add(passwordLabel);

            passwordField = new JPasswordField();
            panel.add(passwordField);
        }

        private void initLoginButton() {
            User user = User.getInstance();
            loginButton = new JButton("Login");
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    RegisterController registerController = new RegisterController();
                    UserController userController = new UserController();

                    if(!userController.isAlreadyExactlyUsed(usernameField.getText()) &&
                            !userController.emailExists(usernameField.getText())) {
                        MyUtility.errorHandler("Date de autentificare invalide!");
                        usernameField.setText("");
                    } else if(!userController.isGoodPassword(usernameField.getText(), new String(passwordField.getPassword()))) {
                        MyUtility.errorHandler("Parola incorecta");
                        passwordField.setText("");
                    }
                    else {
                        user.setLoginTime(LocalTime.now());
                        userController.newLoginTime(LocalTime.now(), user.getId());
                        userController.myUser(usernameField.getText());
                        ActionsController actionsController = new ActionsController();
                        actionsController.addAction(MyUtility.initAction("logged in"));
                        dispose();
                        MainPage mainPage = new MainPage();
                        mainPage.setVisible(true);
                        actionsController.addAction(MyUtility.initAction("accessed Main Page"));
                    }
                }
            });

            panel.add(loginButton);
        }

        private void initCancelButton() {
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(cancelButton.isEnabled()) {
                        dispose();
                        StartPage startPage = new StartPage();
                        startPage.setVisible(true);

                        ActionsController actionsController = new ActionsController();
                        actionsController.addAction(MyUtility.initAction("accessed Start Page"));
                    }
                }
            });
            panel.add(cancelButton);
        }


}
