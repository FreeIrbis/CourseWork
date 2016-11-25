package com.quasar.hibernateh2.dao.impl;

import com.quasar.hibernateh2.dao.AccountDAO;
import com.quasar.hibernateh2.dao.entity.Account;
import com.quasar.hibernateh2.dao.hiber_util.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

/**
 *
 * @author Irbis
 */
public class AccountDAOImpl implements AccountDAO {
     @Override
    public void addAccount(Account account) throws SQLException {
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Account getAccountById(Long id) throws SQLException {
        Session session = null;
        Account account = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            account = (Account) session.get(Account.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }


    @Override
    public Account getAccountByMaxId() throws SQLException {
        Session session = null;
        Account account = null;
        try {
            DetachedCriteria maxId = DetachedCriteria.forClass(Account.class)
    .setProjection( Projections.max("id") );
            session = HibernateUtil.getSessionFactory().openSession();
            account = (Account) session.createCriteria(Account.class).
                    add( Property.forName("id").eq(maxId)).list().get(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return account;
    }
    
    @Override
    public List<Account> getAllAccounts() throws SQLException {
        Session session = null;
        List<Account> accounts = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            accounts = session.createCriteria(Account.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return accounts; 
    }

    @Override
    public void deleteAccount(Account account) throws SQLException {
    Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(account);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
