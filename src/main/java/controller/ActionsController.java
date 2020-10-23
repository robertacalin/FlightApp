package controller;

import dao.ActionsDao;
import model.Actions;

import java.sql.Connection;
import java.util.List;

public class ActionsController {
    private ActionsDao actionsDao;

    public ActionsController() {
        Connection connection = DatabaseConnection.getConnection();
        actionsDao = new ActionsDao(connection);
    }

    public boolean addAction(Actions actions) {
        return this.actionsDao.insertAction(actions);
    }

    public boolean addRegisterAction(Actions actions, int userId) {
        return this.actionsDao.insertRegisterAction(actions, userId);
    }

    public List<Actions> findAction(int id) {
        return this.actionsDao.findByUser(id);
    }
}
