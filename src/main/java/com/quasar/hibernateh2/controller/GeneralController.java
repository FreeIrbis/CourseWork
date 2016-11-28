package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.Association;
import com.quasar.hibernateh2.dao.entity.ProgResource;
import com.quasar.hibernateh2.dao.entity.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Irbis
 */
public class GeneralController extends AbstractController implements Initializable {

    private static final Toolkit kit = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = kit.getScreenSize();

    private static int lx;
    private static int ly;

    public void openSet(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Settings.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Настройки");
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

    /*Описание графических элементов*/
    @FXML
    ListView<Association> ListView = new <Association>ListView();

    @FXML
    MenuBar MenuBar = new MenuBar();
    List<Association> listAssociations = null;
    List<ProgResource> listResources = null;
    public Association association;

    /*Говорит NullPointerException*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {                                                                           /*Вот здесь*/
            User user = getApp().getUser();
            System.out.println("user.getNameDB() = " + user.getLoginUser());
            listAssociations = Factory.getInstance().getAssociationDAO().getAllAssociationsByUser(user);

            //Вот так работает
            /*listAssociations =Factory.getInstance().getAssociationDAO().getAllAssociationsByUser(
             Factory.getInstance().getUserDAO().getUserById(790L));*/
            ListView.setItems(FXCollections.observableArrayList(listAssociations));
        } catch (SQLException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ListClicked() throws SQLException, IOException {
        association = ListView.getFocusModel().getFocusedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Info.fxml"));
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
        /*listResources.add(resource);
         ListView.setItems(FXCollections.observableArrayList(listResources));*/
    }

}
