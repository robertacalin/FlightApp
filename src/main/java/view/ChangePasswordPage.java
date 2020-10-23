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

public class ChangePasswordPage extends JFrame{
    private JPanel panel;

    private JLabel newPasswordLabel, confirmNewPasswordLabel;
    private JPasswordField newPasswordField, confirmNewPasswordField;
    private JButton okButton, cancelButton;
    private EmptyBorder border = new EmptyBorder(0,10,0,0);

    public ChangePasswordPage() {

        initDefaultProperties();
        panel = new JPanel(new GridLayout(3,2));

        initNewPassword();
        initConfirmNewPassword();
        initOkButton();
        initCancelButton();

        add(panel, BorderLayout.CENTER);
        setVisible(true);

    }

    public void initDefaultProperties() {
        setTitle("Change Password");
        setSize(500,150);
        setLocationRelativeTo(null);
    }

    public void initNewPassword() {
        newPasswordLabel = new JLabel("New Password");
        newPasswordLabel.setBorder(border);
        panel.add(newPasswordLabel);

        newPasswordField = new JPasswordField();
        panel.add(newPasswordField);
    }

    public void initConfirmNewPassword() {
        confirmNewPasswordLabel = new JLabel("Confirm Password");
        confirmNewPasswordLabel.setBorder(border);
        panel.add(confirmNewPasswordLabel);

        confirmNewPasswordField = new JPasswordField();
        panel.add(confirmNewPasswordField);
    }

    public void initOkButton() {
        okButton = new JButton("OK");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserController userController = new UserController();
                User user = User.getInstance();
                if (!RegisterController.isValidPassword(new String(newPasswordField.getPassword()))) {
                    MyUtility.errorHandler("Parola trebuie sa aiba minim o litera mica, o litera mare si o cifra! " +
                            "Dimensiune minim 6 caractere");
                } else if (!RegisterController.isConfirmed(new String(newPasswordField.getPassword()),
                        new String(confirmNewPasswordField.getPassword()))) {
                    MyUtility.errorHandler("Campurile de parola si confirmare parola trebuie sa fie identice!");
                } else {
                    userController.newPassword(new String(newPasswordField.getPassword()), user.getPassword());
                    user.setPassword(new String(newPasswordField.getPassword()));
                    JOptionPane.showMessageDialog(panel,
                            "Parola schimbata cu succes.");
                    ActionsController actionsController = new ActionsController();
                    actionsController.addAction(MyUtility.initAction("changed password"));
                    actionsController.addAction(MyUtility.initAction("logged out"));
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    actionsController.addAction(MyUtility.initAction("accessed Login Page"));
                }
            }
        }
        );

        panel.add(okButton);
    }

    public void initCancelButton() {
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(cancelButton.isEnabled()) {
                    dispose();
                    MyAccount myAccount = new MyAccount();
                    myAccount.setVisible(true);
                    ActionsController actionsController = new ActionsController();
                    actionsController.addAction(MyUtility.initAction("accessed MyAccount Page"));
                }
            }
        });

        panel.add(cancelButton);
    }
}
