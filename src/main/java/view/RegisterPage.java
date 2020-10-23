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

public class RegisterPage extends JFrame{

    private JPanel panel;
    private JButton cancelButton, registerButton;
    private JTextField usernameField, emailField;
    private JPasswordField passwordField, passwordConfirmationField;
    private JLabel usernameLabel, emailLabel, passwordLabel, passwordConfirmationLabel;
    private EmptyBorder border = new EmptyBorder(0,10,0,0);

    public RegisterPage() {

        initDefaultProperties();
        panel = new JPanel(new GridLayout(5,2));

        initUsername();
        initPassword();
        initConfirmPassword();
        initEmail();
        initCancelButton();
        initRegisterButton();

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initDefaultProperties() {
        setTitle("Register");
        setSize(500,300);
        setLocationRelativeTo(null);
    }

    private void initUsername() {
        usernameLabel = new JLabel("Username");
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

    private void initConfirmPassword() {
        passwordConfirmationLabel = new JLabel("Password confirmation");
        passwordConfirmationLabel.setBorder(border);
        panel.add(passwordConfirmationLabel);

        passwordConfirmationField = new JPasswordField();
        panel.add(passwordConfirmationField);
    }

    private void initEmail() {
        emailLabel = new JLabel("E-mail");
        emailLabel.setBorder(border);
        panel.add(emailLabel);

        emailField = new JTextField();
        panel.add(emailField);
    }

    private void initCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cancelButton.isEnabled()) {
                    dispose();
                }
            }
        });

        panel.add(cancelButton);
    }

    private void initRegisterButton() {
        registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = initUser();
                RegisterController registerController = new RegisterController();
                UserController userController = new UserController();

                if(usernameField.getText().isEmpty()) {
                    MyUtility.errorHandler("Campul de username trebuie completat obligatoriu!");
                } else if(!RegisterController.isValidPassword(new String(passwordField.getPassword()))) {
                    MyUtility.errorHandler("Parola trebuie sa aiba minim o litera mica, o litera mare si o cifra! " +
                            "Dimensiune minim 6 caractere");
                } else if(!RegisterController.isConfirmed(new String(passwordField.getPassword()),
                        new String(passwordConfirmationField.getPassword()))) {
                    MyUtility.errorHandler("Campurile de parola si confirmare parola trebuie sa fie identice!");
                } else if(!RegisterController.isValidEmailAddress(emailField.getText())) {
                    MyUtility.errorHandler("Adresa de e-mail invalida");
                } else if(registerController.isAlreadyUsed(usernameField.getText())) {
                    MyUtility.errorHandler("Username deja existent");
                } else if(userController.emailExists(emailField.getText())) {
                    MyUtility.errorHandler("E-mail deja existent!");
                }
                else {
                    registerController.addUser(user);
                    ActionsController actionsController = new ActionsController();
                    int userId = userController.getIdByEmail(emailField.getText());
                    actionsController.addRegisterAction(MyUtility.initAction("registered"), userId);
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                }
            }
        });

        panel.add(registerButton);
    }

    private User initUser() {
        User user = User.getInstance();

        user.setUsername(usernameField.getText());
        user.setPassword(new String(passwordField.getPassword()));
        user.setEmail(emailField.getText());
        user.setLoginTime(LocalTime.now());

        return user;
    }

}
