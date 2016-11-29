package com.quasar.hibernateh2.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Irbis
 */
public class LoginController extends AbstractController implements Initializable {
     
    private static final Toolkit kit = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = kit.getScreenSize();

    private static int lx;
    private static int ly;
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
        if (application == null){
            errorMessage.setText("Hello " + login.getText());
        } else {
            if (!application.userLogging(login.getText(), password.getText())){
                errorMessage.setText("Username/Password is incorrect");
            }
        }
    }
    public void openReg() throws IOException{
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Информация");
        stage.setScene(new Scene(root1));
        Scene scene = stage.getScene();
        scene.getStylesheets().add("/styles/Login.css");
        stage.setResizable(false);
        // установка иконки
        Image ix = new Image("/icon/lock.png");
        stage.getIcons().add(ix);
        stage.centerOnScreen();

        lx = screenSize.width;
        ly = screenSize.height;

        double x = lx / 2 - 600 / 2;
        double y = ly / 2 - 400 / 2;

        stage.setX(x);
        stage.setY(y);

        stage.show();
    }
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
