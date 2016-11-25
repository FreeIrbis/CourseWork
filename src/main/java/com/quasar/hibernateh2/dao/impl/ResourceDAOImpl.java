package com.quasar.hibernateh2.dao.impl;

import com.quasar.hibernateh2.dao.ResourceDAO;
import com.quasar.hibernateh2.dao.entity.ProgResource;
import com.quasar.hibernateh2.dao.hiber_util.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

public class ResourceDAOImpl implements ResourceDAO {
    
    @Override
    public void addResource(ProgResource resource) throws SQLException {
        Session session = null;
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(resource);
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
    public void updateResource(ProgResource resource) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(resource);
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
    public ProgResource getResourceById(Long id) throws SQLException {
        Session session = null;
        ProgResource resource = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            resource = (ProgResource) session.get(ProgResource.class, id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return resource;
    }
    
    @Override
    public ProgResource getResourceByMaxId() throws SQLException {
        Session session = null;
        ProgResource resource = null;
        try {
            DetachedCriteria maxId = DetachedCriteria.forClass(ProgResource.class)
    .setProjection( Projections.max("id") );
            session = HibernateUtil.getSessionFactory().openSession();
            resource = (ProgResource) session.createCriteria(ProgResource.class).
                    add( Property.forName("id").eq(maxId)).list().get(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return resource;
    }

    @Override
    public List<ProgResource> getAllResources() throws SQLException {
        Session session = null;
        List<ProgResource> resource = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            resource = session.createCriteria(ProgResource.class).list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка I/O", JOptionPane.OK_OPTION);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return resource; 
    }

    @Override
    public void deleteResource(ProgResource resource) throws SQLException {
    Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(resource);
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
