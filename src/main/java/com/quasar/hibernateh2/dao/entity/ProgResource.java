/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quasar.hibernateh2.dao.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ghost
 */
@Entity
@Table(name = "resource")
public class ProgResource extends Model implements Serializable {
    
    @Column(name = "nameRes")
    private String nameRes;

    public String getNameRes() {
        return nameRes;
    }
    
    @OneToMany(mappedBy = "resource")
    private Set<Association> associations;

    public Set<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(Set<Association> associations) {
        this.associations = associations;
    }
    

    public void setNameRes(String nameRes) {
        this.nameRes = nameRes;
    }

    @Column(name = "urlRes")
    private String urlRes;
    
    public String getUrlRes() {
        return urlRes;
    }

    public void setUrlRes(String urlRes) {
        this.urlRes = urlRes;
    }
       
    public ProgResource() {
    }
    
    public ProgResource(String nameRes,String urlRes) {
        this.nameRes=nameRes;
        this.urlRes=urlRes;
    }
     @Override
    public String toString() {
        return getNameRes();
    }
    
}

