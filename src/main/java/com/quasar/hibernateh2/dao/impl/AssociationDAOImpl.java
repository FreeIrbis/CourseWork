package com.quasar.hibernateh2.dao.impl;

import com.quasar.hibernateh2.dao.AssociationDAO;
import com.quasar.hibernateh2.dao.entity.Association;
import com.quasar.hibernateh2.dao.entity.User;
import com.quasar.hibernateh2.dao.hiber_util.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Property;

/**
 *
 * @author Irbis
 */
public class AssociationDAOImpl implements AssociationDAO {
     @Override
    public void addAssociation(Association association) throws SQLException {
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(association);
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
    public void updateAssociation(Association association) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(association);
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
    public Association getAssociationById(Long id) throws SQLException {
        Session session = null;
        Association association = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            association = (Association) session.get(Association.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return association;
    }

    @Override
    public List<Association> getAllAssociations() throws SQLException {
        Session session = null;
        List<Association> associations = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            associations = session.createCriteria(Association.class).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return associations; 
    }

     @Override
    public List<Association> getAllAssociationsByUser(User user) throws SQLException {
        Session session = null;
        List<Association> associations = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            associations = session.createCriteria(Association.class).
                    add( Property.forName("user").eq(user)).list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return associations; 
    }
    
    @Override
    public Association getAssociationByUser(User user) throws SQLException {
        Session session = null;
        Association association = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            association = (Association) session.createCriteria(Association.class).
                    add( Property.forName("user").eq(user)).list().get(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return association; 
    }
    
    @Override
    public void deleteAssociation(Association association) throws SQLException {
    Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(association);
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
