package com.quasar.hibernateh2.dao;

import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author Irbis
 */
public interface ResourceDAO {
    
    public void addResource(Resource resource) throws SQLException;

    public void updateResource(Resource recourse) throws SQLException;

    public Resource getResourceById(Long id) throws SQLException;

    public List<Resource> getAllResources() throws SQLException;

    public void deleteResource(Resource resource) throws SQLException;
}
