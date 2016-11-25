/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quasar.hibernateh2.test;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghost
 */
public class TestAuthenticator {
    public static void main(String[] args) {
        try {
            User user = Factory.getInstance().getUserDAO().getUserBySearch("one", "one");
            System.out.println(user.getLoginUser());
        } catch (SQLException ex) {
            Logger.getLogger(TestAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
