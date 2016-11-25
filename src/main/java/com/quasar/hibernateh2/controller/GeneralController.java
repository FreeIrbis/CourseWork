package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.Account;
import com.quasar.hibernateh2.dao.entity.ProgResource;
import com.sun.javafx.scene.control.skin.LabeledText;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.annotations.common.util.impl.Log;

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
    ListView<ProgResource> ListView = new <ProgResource>ListView();

    @FXML
    MenuBar MenuBar = new MenuBar();
    List<ProgResource> listResources = null;
    public ProgResource resource;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listResources = Factory.getInstance().getResourceDAO().getAllResources();
            ListView.setItems(FXCollections.observableArrayList(listResources));
        } catch (SQLException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ListClicked()throws SQLException, IOException{
        resource = ListView.getFocusModel().getFocusedItem();
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
