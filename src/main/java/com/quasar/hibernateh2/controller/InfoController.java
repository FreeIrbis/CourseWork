package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    TextField Login;
    @FXML
    PasswordField Password;
    @FXML
    TextField passwordDB;
    @FXML
    TextField UrlResource;
    @FXML
    TextArea Other;
    @FXML
    Button userButton;

    User user = new User();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            user = Factory.getInstance().getUserDAO().getUserById(1L);
        } catch (SQLException ex) {
            Logger.getLogger(InfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Login.setText(user.getLoginUser());
        Password.setText(user.getPassUser());
    }

    public void updateUser() throws SQLException {
        user.setLoginUser(Login.getText().trim());
        user.setPassUser(Password.getText().trim());
        Factory.getInstance().getUserDAO().updateUser(user);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Оповещенпие");
//        alert.setHeaderText(null);
//        alert.setContentText("Данные для входа успешно обновлены");
//
//        alert.showAndWait();

    }
}
