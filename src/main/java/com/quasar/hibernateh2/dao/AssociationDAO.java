package com.quasar.hibernateh2.dao;

import com.quasar.hibernateh2.dao.entity.Association;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Irbis
 */
public interface AssociationDAO {
    
    public void addAssociation(Association association) throws SQLException;

    public void updateAssociation(Association association) throws SQLException;

    public Association getAssociationById(Long id) throws SQLException;

    public List<Association> getAllAssociations() throws SQLException;

    public void deleteAssociation(Association association) throws SQLException;
}
