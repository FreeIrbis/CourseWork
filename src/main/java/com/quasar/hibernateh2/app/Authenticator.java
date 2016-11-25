package com.quasar.hibernateh2.app;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    public User user = new User();
    
    private static final Map<String, String> USERS = new HashMap<String, String>();
    static {
        USERS.put("demo", "demo");
    }
    public boolean validate(String login, String password) {
        user = null;
        try {
            user = Factory.getInstance().getUserDAO().getUserBySearch(login, password);
           if (user!=null){
           return true;
           }
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
    Authenticator(){
    }
}