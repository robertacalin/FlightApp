package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPage extends JFrame {

    public JButton registerButton, loginButton;
    public JPanel panel;

    public StartPage() {

        initDefaultProperties();
        panel = new JPanel(new GridLayout(1,2));
        initRegisterButton();
        initLoginButton();


        add(panel, BorderLayout.CENTER);
        setVisible(true);

    }

    public void initDefaultProperties() {
        setTitle("Start");
        setSize(300,100);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initRegisterButton() {
        registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(registerButton.isEnabled()) {
                    dispose();
                    RegisterPage registerPage = new RegisterPage();
                    registerPage.setVisible(true);
                    //ActionsController actionsController = new ActionsController();
                    //actionsController.addAction(MyUtility.initAction("accessed Register Page"));
                }
            }
        }
        );

        panel.add(registerButton);
    }

    public void initLoginButton() {
        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(loginButton.isEnabled()) {
                    dispose();
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    //ActionsController actionsController = new ActionsController();
                    //actionsController.addAction(MyUtility.initAction("accessed Login Page"));
                }
            }
        }
        );

        panel.add(loginButton);
    }
}
