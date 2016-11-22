package com.quasar.hibernateh2.controller;

import java.io.IOException;
import java.net.URL;
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
 * @author Irbis
 */
public class LoginController extends AbstractController implements Initializable {
    
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    Button loginButton;
    @FXML
    Label errorMessage;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        // Parent root1 = FXMLLoader.load(getClass().getResource("/fxml/General.fxml"));
//        System.out.println("You clicked me!");
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root1));
//        stage.show();
        if (application == null){
            errorMessage.setText("Hello " + login.getText());
        } else {
            if (!application.userLogging(login.getText(), password.getText())){
                errorMessage.setText("Username/Password is incorrect");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
