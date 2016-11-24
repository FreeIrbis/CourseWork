package com.quasar.hibernateh2.dao;

import com.quasar.hibernateh2.dao.impl.UserDAOImpl;
import com.quasar.hibernateh2.dao.impl.AccountDAOImpl;
import com.quasar.hibernateh2.dao.impl.ResourceDAOImpl;
import com.quasar.hibernateh2.dao.impl.AssociationDAOImpl;


public class Factory {

   private static UserDAO UserDAO = null;
    private static AccountDAO AccountDAO = null;
    private static ResourceDAO ResourceDAO = null;    
    private static AssociationDAO AssociationDAO = null;
    private static Factory instance = null;
  
    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
    
    public AccountDAO getAccountDAO() {
        if (AccountDAO == null) {
            AccountDAO = new AccountDAOImpl();
        }
        return AccountDAO;
    }
    
     public UserDAO getUserDAO() {
        if (UserDAO == null) {
            UserDAO = new UserDAOImpl();
        }
        return UserDAO;
    }
     
     public AssociationDAO getAssociationDAO() {
        if (AssociationDAO == null) {
            AssociationDAO = new AssociationDAOImpl();
        }
        return AssociationDAO;
    }
          
     public ResourceDAO getResourceDAO() {
        if (ResourceDAO == null) {
            ResourceDAO = new ResourceDAOImpl();
        }
        return ResourceDAO;
    }

}
