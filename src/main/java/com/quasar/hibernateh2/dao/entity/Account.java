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
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends Model implements Serializable{
    
    @OneToMany(mappedBy = "account")
    private Set<Association> associations;
    
    @Column(name = "loginAccount")
    private String loginAccount;

    public String getLoginAccount() {
        return loginAccount;
    }

    public Set<Association> getAssociations() {
        return associations;
    }

    public void setAssociations(Set<Association> associations) {
        this.associations = associations;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getPassAccount() {
        return passAccount;
    }

    public void setPassAccount(String passAccount) {
        this.passAccount = passAccount;
    }

    @Column(name = "passwordAccount")
    private String passAccount;
    
       
    public Account() {
    }
    
    public Account(String loginAccount,String passAccount) {
        this.loginAccount=loginAccount;
        this.passAccount=passAccount;
    }
    
}
