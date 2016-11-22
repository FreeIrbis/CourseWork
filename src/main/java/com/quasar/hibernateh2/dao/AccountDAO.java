package com.quasar.hibernateh2.dao;

import com.quasar.hibernateh2.dao.entity.Account;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Irbis
 */
public interface AccountDAO {
    
     public void addAccount(Account account) throws SQLException;

    public void updateAccount(Account account) throws SQLException;

    public Account getAccountById(Long id) throws SQLException;

    public List<Account> getAllAccounts() throws SQLException;

    public void deleteAccount(Account account) throws SQLException;
    
}
