/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author Ghost
 */
public class RegistrationController extends AbstractController implements Initializable {
    
    @FXML
    TextField loginReg;
    @FXML
    PasswordField passReg;
    @FXML
    Button loginButton;
    @FXML
    PasswordField passRegSub;
    @FXML
    Label errorMessage;
    User user=new User();
    boolean b;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
       }
    
    public void SubmitReg(ActionEvent event) throws SQLException, IOException{
       b=Factory.getInstance().getUserDAO().checkUserBySearch(loginReg.getText(), passReg.getText());
       System.out.println(b);
       if(b==true && (passRegSub.getText().equals(passReg.getText()))){
           user.setLoginUser(loginReg.getText().trim());
           user.setPassUser(passReg.getText().trim());
           Factory.getInstance().getUserDAO().addUser(user);
           
       }else{
           errorMessage.setText("Password is incorrect/User already exists ");
       }
       b=false;
       user.setLoginUser("");
       user.setPassUser("");
    }
}
