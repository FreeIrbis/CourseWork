/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quasar.hibernateh2.dao.entity;

/**
 *
 * @author Ghost
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ghost
 */
@Entity
@Table(name = "association")
public class Association extends Model implements Serializable {
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ProgResource.class)
    @JoinColumn(name = "resource")
    private ProgResource resource;

    public ProgResource getRecourse() {
        return resource;
    }

    public void setRecourse(ProgResource recourse) {
        this.resource = recourse;
    }
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Account.class)
    @JoinColumn(name = "account")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
       
     @Column(name = "about")
    private String about;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
     
    public Association (){
    }
}

