/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quasar.hibernateh2.dao;

import com.quasar.hibernateh2.dao.entity.User;
import java.sql.SQLException;

/**
 *
 * @author Irbis
 */
public interface UserDAO {
    
    public void addUser(User user) throws SQLException;

    public void updateUser(User user) throws SQLException;
    
    public User getUserById(Long id) throws SQLException;
    
    public User getUserByTempId(Long id) throws SQLException;
    
    public User getUserBySearch(String Login, String Pass) throws SQLException;
    
}
