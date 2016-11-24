package com.quasar.hibernateh2.dao;

import com.quasar.hibernateh2.dao.entity.ProgResource;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.Resource;

/**
 *
 * @author Irbis
 */
public interface ResourceDAO {
    
    public void addResource(ProgResource resource) throws SQLException;

    public void updateResource(ProgResource recourse) throws SQLException;

    public ProgResource getResourceById(Long id) throws SQLException;

    public List<ProgResource> getAllResources() throws SQLException;

    public void deleteResource(ProgResource resource) throws SQLException;
}
