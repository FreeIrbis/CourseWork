
package com.quasar.hibernateh2.test;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author artur
 */
public class TestUser {
    public static void main(String[] args) {
        try {
            Factory.getInstance().getUserDAO().addUser(new User(1L, "demo", "demo"));
            User u = Factory.getInstance().getUserDAO().getUserByTempId(1L);
            System.out.println(u.getLoginUser());
        } catch (SQLException ex) {
            Logger.getLogger(TestUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.exit(0);
        }
    }
}
