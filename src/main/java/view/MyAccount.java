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
import java.time.Duration;
import java.time.LocalTime;

public class MyAccount extends JFrame {

    JLabel usernameLabel, emailLabel, newUsernameLabel, newEmailLabel;
    JButton changeUsernameButton, changeEmailButton, changePasswordButton;
    JTextField newUsernameField, newEmailField;
    JPanel panel;
    private EmptyBorder border = new EmptyBorder(0,10,0,0);

    public MyAccount() {
        initDefaultProperties();

        panel = new JPanel(new GridLayout(3,3));

        initMyAccount();
        initChangePasswordButton();
        initUsername();
        initChangeUsernameButton();
        initEmail();
        initChangeEmailButton();

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void initDefaultProperties() {
        MyUtility.initMenu(this);
        setTitle("MyAccount");
        setSize(500,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMyAccount() {
        User user = User.getInstance();

        usernameLabel = new JLabel(user.getUsername());
        emailLabel = new JLabel(user.getEmail());

        panel.add(usernameLabel);
        panel.add(emailLabel);
    }

    private void initChangePasswordButton() {
        User user = User.getInstance();
        changePasswordButton = new JButton("Change password");
        changePasswordButton.addActionListener(new ActionListener() {
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
                    ChangePasswordPage changePasswordPage = new ChangePasswordPage();
                    changePasswordPage.setVisible(true);
                    ActionsController actionsController = new ActionsController();
                    actionsController.addAction(MyUtility.initAction("accessed Change Password Page"));
                }
            }
        });

        panel.add(changePasswordButton);
    }

    private void initUsername() {
        newUsernameLabel = new JLabel("Change username");
        newUsernameLabel.setBorder(border);
        panel.add(newUsernameLabel);

        newUsernameField = new JTextField();
        panel.add(newUsernameField);
    }

    private void initChangeUsernameButton() {
        UserController userController = new UserController();
        RegisterController registerController =  new RegisterController();
        User user = User.getInstance();

        changeUsernameButton = new JButton("Change username");
        changeUsernameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Duration.between(user.getLoginTime(), LocalTime.now()).toMinutes() >= 15) {
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    ActionsController actionsController = new ActionsController();
                    actionsController.addAction(MyUtility.initAction("accessed Login Page"));
                } else if(newUsernameField.getText().isEmpty()) {
                    MyUtility.errorHandler("Campul de username trebuie completat obligatoriu!");
                } else if(registerController.isAlreadyUsed(newUsernameField.getText())) {
                    MyUtility.errorHandler("Username deja existent");
                } else {
                    userController.newUsername(newUsernameField.getText(), user.getUsername());
                    user.setUsername(newUsernameField.getText());
                    JOptionPane.showMessageDialog(panel,
                            "Username schimbat cu succes.");
                    usernameLabel.setText(user.getUsername());
                    newUsernameField.setText("");
                    ActionsController actionsController = new ActionsController();
                    actionsController.addAction(MyUtility.initAction("changed username"));
                }
            }
        });
        panel.add(changeUsernameButton);
    }

    private void initEmail() {
        newEmailLabel = new JLabel("Change e-mail");
        newEmailLabel.setBorder(border);
        panel.add(newEmailLabel);

        newEmailField = new JTextField();
        panel.add(newEmailField);
    }

    private void initChangeEmailButton() {
        User user = User.getInstance();
        UserController userController = new UserController();

        changeEmailButton = new JButton("Change e-mail");
        changeEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(Duration.between(user.getLoginTime(), LocalTime.now()).toMinutes() >= 15) {
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    ActionsController actionsController = new ActionsController();
                    actionsController.addAction(MyUtility.initAction("accessed Login Page"));
                } else if(!RegisterController.isValidEmailAddress(newEmailField.getText())) {
                    MyUtility.errorHandler("Adresa de e-mail invalida");
                } else if(userController.emailExists(newEmailField.getText())) {
                    MyUtility.errorHandler("E-mail deja existent!");
                } else {
                    userController.newEmail(newEmailField.getText(), user.getEmail());
                    user.setEmail(newEmailField.getText());
                    JOptionPane.showMessageDialog(panel,
                            "E-mail schimbat cu succes.");
                    emailLabel.setText(user.getEmail());
                    newEmailField.setText("");
                    ActionsController actionsController = new ActionsController();
                    actionsController.addAction(MyUtility.initAction("changed e-mail"));
                }
            }
        });
        panel.add(changeEmailButton);
    }

}
