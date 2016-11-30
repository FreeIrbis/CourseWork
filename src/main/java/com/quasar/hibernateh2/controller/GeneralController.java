package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.Account;
import com.quasar.hibernateh2.dao.entity.Association;
import com.quasar.hibernateh2.dao.entity.ProgResource;
import com.quasar.hibernateh2.dao.entity.User;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Irbis
 */
public class GeneralController extends AbstractController implements Initializable {

    ProgResource resource;
    Account account;
    private static final Toolkit kit = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = kit.getScreenSize();

    private static int lx;
    private static int ly;

    /*Описание графических элементов*/
    @FXML
    public ListView<Association> ListView = new <Association>ListView();

    @FXML
    MenuBar MenuBar = new MenuBar();
    List<ProgResource> listResources = null;
    Association association;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            User user = getApp().getUser();
            System.out.println("user.getNameDB() = " + user.getLoginUser());
            getApp().updateListAss();
            ListView.setItems(FXCollections.observableArrayList(getApp().getListAss()));
        } catch (SQLException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ListClicked() throws SQLException, IOException {
        if (ListView.getFocusModel().getFocusedItem() != null) {
            association = ListView.getFocusModel().getFocusedItem();
            getApp().setTempAss(association);
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
        } else {
            association = null;
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

            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

                @Override

                public void handle(WindowEvent event) {
                    event.consume();

                }

            });
        }

    }

    public void addAssociation() throws IOException {
        association = null;
        getApp().setTempAss(association);
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
    }

    public void DeleteAss() throws SQLException {
        if (ListView.getFocusModel().getFocusedItem() != null) {
            association = ListView.getFocusModel().getFocusedItem();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Удаление");
            alert.setHeaderText("Запись " + association.getRecourse().getNameRes() + " будет удалена");
            alert.setContentText("Вы действительно хотите удалить запись?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
               resource = association.getRecourse();
               account = association.getAccount();
               Factory.getInstance().getAssociationDAO().deleteAssociation(association);
               Factory.getInstance().getAccountDAO().deleteAccount(account);
               Factory.getInstance().getResourceDAO().deleteResource(resource);
               getApp().updateListAss();
                ListView.setItems(FXCollections.observableArrayList(getApp().getListAss()));
            }
                       
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Оповещенпие");
            alert.setHeaderText(null);
            alert.setContentText("Запись для удаления не выбрана");
            alert.showAndWait();

        }
    }

    public void Open(){
    
    }
    
    public void updateList() throws SQLException {
        getApp().updateListAss();
        ListView.setItems(FXCollections.observableArrayList(getApp().getListAss()));
    }
}
