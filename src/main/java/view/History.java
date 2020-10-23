package view;

import controller.ActionsController;
import model.Actions;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class History extends JFrame {
    public JPanel panel;
    public JLabel text, theUser;

    public History() {

        initDefaultProperties();
        panel = new JPanel(new GridLayout(21,1));
        initText();

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void  initDefaultProperties() {
        setTitle("History");
        setSize(500,500);
        setLocationRelativeTo(null);
    }

    public void initText() {
        ActionsController actionsController = new ActionsController();
        User user = User.getInstance();

        List<Actions> actions = actionsController.findAction(user.getId());
        ArrayList<Actions> actionsList = new ArrayList<>(actions);

        theUser = new JLabel(user.getUsername());
        panel.add(theUser);
        boolean done = false;
        for(int i = actionsList.size() - 1; i >= 0 && !done; i--) {
            if (actionsList.size() - i >= 20)
                done = true;
            String action = actionsList.get(i).getAction() + " at " + actionsList.get(i).getTime();
            text = new JLabel(action);

            panel.add(text);
        }


    }
}
