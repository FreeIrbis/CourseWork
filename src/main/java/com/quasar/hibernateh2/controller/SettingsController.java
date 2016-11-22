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
import javafx.scene.control.TextField;

/**
 *
 * @author Irbis
 */
public class SettingsController extends AbstractController implements Initializable {

    @FXML
    TextField loginUser;
    @FXML
    TextField passwordUser;
    @FXML
    TextField loginDB;
    @FXML
    TextField passwordDB;
    @FXML
    TextField linkDB;
    @FXML
    TextField nameDB;
    @FXML
    Button userButton;
    @FXML
    Button dbButton;

    User user = new User();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            user = Factory.getInstance().getUserDAO().getUserById(1L);
        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        loginUser.setText(user.getLoginUser());
        passwordUser.setText(user.getPassUser());
    }

    public void updateUser() throws SQLException {
        user.setLoginUser(loginUser.getText().trim());
        user.setPassUser(passwordUser.getText().trim());
        Factory.getInstance().getUserDAO().updateUser(user);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Оповещенпие");
//        alert.setHeaderText(null);
//        alert.setContentText("Данные для входа успешно обновлены");
//
//        alert.showAndWait();

    }
}
