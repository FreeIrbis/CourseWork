/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quasar.hibernateh2.test;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.Account;
import com.quasar.hibernateh2.dao.entity.Association;
import com.quasar.hibernateh2.dao.entity.ProgResource;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghost
 */
public class TestAssociation {
    
    public static void main(String[] args) {
        Association association = new Association();
        Account account = new Account();
        ProgResource resource = new ProgResource();
        try {    
            account.setLoginAccount("demoLogin");
            account.setPassAccount("demoPassword");
            Factory.getInstance().getAccountDAO().addAccount(account);
            account = Factory.getInstance().getAccountDAO().getAccountByMaxId();
            resource.setNameRes("demoRes");
            resource.setUrlRes("demoURLResource");
            Factory.getInstance().getResourceDAO().addResource(resource);
            Factory.getInstance().getResourceDAO().getResourceByMaxId();
            association.setUser(Factory.getInstance().getUserDAO().getUserByTempId(1L));
            association.setAbout("Test text gor association");
            association.setAccount(account);
            association.setRecourse(resource);
            Factory.getInstance().getAssociationDAO().addAssociation(association);
        } catch (SQLException ex) {
            Logger.getLogger(TestAssociation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
