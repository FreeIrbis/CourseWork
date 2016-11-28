package com.quasar.hibernateh2.app;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.sql.SQLException;

public class Authenticator {
    User user;
    public boolean validate(String login, String password) {
        user = null;
        try {
            user = Factory.getInstance().getUserDAO().getUserBySearch(login, password);
           if (user!=null){
           user = Factory.getInstance().getUserDAO().getUserBySearch(login, password);
           return true;
           }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
    public User getUser(){
        return user;  
    }
}