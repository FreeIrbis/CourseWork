package com.quasar.hibernateh2.app;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.sql.SQLException;

public class Authenticator {

    public User validate(String login, String password) {
        User user = null;
        try {
            user = Factory.getInstance().getUserDAO().getUserBySearch(login, password);
        } catch (SQLException e) {
            return user;
        }
        return user;
    }
}
