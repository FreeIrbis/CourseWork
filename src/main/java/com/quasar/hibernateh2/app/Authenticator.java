package com.quasar.hibernateh2.app;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    public static User user;
    private static final Map<String, String> USERS = new HashMap<String, String>();
    static {
        USERS.put("demo", "demo");
    }
    public static boolean validate(String login, String password) {
        try {
            user = Factory.getInstance().getUserDAO().getUserByTempId(1L);
            boolean loginB = login != null ? login.equals(user.getLoginUser()) : false;
            boolean passB = password != null ? password.equals(user.getPassUser()) : false;
            return loginB && passB;
        } catch (SQLException ex) {
            return false;
        }
    }
}