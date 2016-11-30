package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.Account;
import com.quasar.hibernateh2.dao.entity.Association;
import com.quasar.hibernateh2.dao.entity.ProgResource;
import com.quasar.hibernateh2.dao.entity.User;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

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
    Account account;
    ProgResource resource;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            association = getApp().getTempAss();
        if (association != null) {
            NameResource.setText(association.getRecourse().getNameRes());
            UrlResource.setText(association.getRecourse().getUrlRes());

            Login.setText(association.getAccount().getLoginAccount());
            Password.setText(association.getAccount().getPassAccount());

            Other.setText(association.getAbout());
        }else{
        Password.setText("");
        Login.setText("");
        NameResource.setText("");
        UrlResource.setText("");
        Other.setText("");}
    }

    
    public TextField getLoginField() {
        return Login;
    }

    public TextField getPassField() {
        return Password;
    }

    public TextField getNameResField() {
        return NameResource;
    }

    public TextField getURLResField() {
        return UrlResource;
    }

    public TextArea getLabel() {
        return Other;
    }

    public static void copyToSystemClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }

    public void CheckingPass() {
        if (LookPass.isSelected()) {
            /*Снятие маски с текста в поле*/
            Password.setStyle("-fx-echo-char:;");
        } else {
            Password.setStyle("-fx-echo-char:*;");
        }
    }

    public void CopyURL() {
        copyToSystemClipboard(association.getRecourse().getUrlRes());
    }

    public void CopyLogin() {
        copyToSystemClipboard(association.getAccount().getLoginAccount());
    }

    public void CopyPass() {
        copyToSystemClipboard(association.getAccount().getPassAccount());
    }

    public void updateUser() throws SQLException {
        if (association != null) {

            resource = association.getRecourse();
            resource.setNameRes(NameResource.getText());
            resource.setUrlRes(UrlResource.getText());
            Factory.getInstance()
                    .getResourceDAO()
                    .updateResource(resource);

            account = association.getAccount();
            account.setLoginAccount(Login.getText());
            account.setPassAccount(Password.getText());
            Factory.getInstance().getAccountDAO().updateAccount(account);
            association.setAbout(Other.getText());
            Factory.getInstance().getAssociationDAO().updateAssociation(association);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Оповещенпие");
            alert.setHeaderText(null);
            alert.setContentText("Данные изменены");
            alert.showAndWait();
        } else {
            association = new Association();
            Factory.getInstance().getAccountDAO().addAccount(new Account(Login.getText(), Password.getText()));
            account = Factory.getInstance().getAccountDAO().getAccountByMaxId();
            Factory.getInstance().getResourceDAO().addResource(new ProgResource(NameResource.getText(), UrlResource.getText()));
            resource = Factory.getInstance().getResourceDAO().getResourceByMaxId();
            association.setAbout(Other.getText());
            association.setAccount(account);
            association.setRecourse(resource);
            association.setUser(getApp().getUser());
            Factory.getInstance().getAssociationDAO().addAssociation(association);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Оповещенпие");
            alert.setHeaderText(null);
            alert.setContentText("Данные добавлены");

            alert.showAndWait();
        }

        getApp().updateListAss();
    }
    
}
