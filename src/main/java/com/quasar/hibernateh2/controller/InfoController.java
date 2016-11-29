package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.Association;
import com.quasar.hibernateh2.dao.entity.User;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Irbis
 */
public class InfoController extends AbstractController implements Initializable {

    @FXML
    TextField NameResource;
    @FXML
    TextField UrlResource;
    @FXML
    TextField Login;
    @FXML
    TextField Password;  
    @FXML
    CheckBox LookPass;
    @FXML
    TextArea Other;
    @FXML
    Button userButton;

    Association association;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
             User user = getApp().getUser();
            association=Factory.getInstance().getAssociationDAO().getAssociationByUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        NameResource.setText(association.getRecourse().getNameRes());
        UrlResource.setText(association.getRecourse().getUrlRes());
        
        Login.setText(association.getAccount().getLoginAccount());
        Password.setText(association.getAccount().getPassAccount());
        
        Other.setText(association.getAbout());
        Password.setStyle("-fx-echo-char:*;");
        
    }

    public static void copyToSystemClipboard(String str) {
    StringSelection ss = new StringSelection(str);
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
}
    
    public void CheckingPass(){
    if (LookPass.isSelected()){
        /*Снятие маски с текста в поле*/
        Password.setStyle("-fx-echo-char:;");  
    }else{
        Password.setStyle("-fx-echo-char:*;");
    }
    }
    
    public void CopyURL(){
       copyToSystemClipboard(association.getRecourse().getUrlRes());
       System.out.println("11111");
    }
    
    public void CopyLogin(){
       copyToSystemClipboard(association.getAccount().getLoginAccount());
       System.out.println("222222");
    }
    
    public void CopyPass(){
       copyToSystemClipboard(association.getAccount().getPassAccount());
       System.out.println("333333");
    }
    public void updateUser() throws SQLException {
        
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Оповещенпие");
//        alert.setHeaderText(null);
//        alert.setContentText("Данные для входа успешно обновлены");
//
//        alert.showAndWait();

    }
}
