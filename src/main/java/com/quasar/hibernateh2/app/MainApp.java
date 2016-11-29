package com.quasar.hibernateh2.app;

import com.quasar.hibernateh2.controller.GeneralController;
import com.quasar.hibernateh2.controller.LoginController;
import com.quasar.hibernateh2.controller.InfoController;
import com.quasar.hibernateh2.dao.Factory;
import com.quasar.hibernateh2.dao.entity.Association;
import com.quasar.hibernateh2.dao.entity.User;
import com.quasar.hibernateh2.dao.hiber_util.HibernateUtil;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 *
 * @author Irbis
 */
public class MainApp extends Application {

    private static final Toolkit kit = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = kit.getScreenSize();

    private static int lx;
    private static int ly;

    private static final String TITLE = "PassLoger";
    private Stage stage;

    private User user;
    private Authenticator auth = new Authenticator();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        lx = screenSize.width;
        ly = screenSize.height;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            stage.setTitle(TITLE);
            // установка иконки
            Image ix = new Image("/icon/lock.png");
            stage.getIcons().add(ix);
            gotoLogin();
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    try {
                        stop();
                    } catch (Exception ex) {
                        Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            int xLogin = 310;
            int yLogin = 420;
            LoginController login = (LoginController) replaceSceneContent("/fxml/Login.fxml", xLogin, yLogin);
            login.setApp(this);
            Scene scene = stage.getScene();
            scene.getStylesheets().add("/styles/Login.css");
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
            // запрет изменения размера окна
            stage.setResizable(false);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoRegisters() {
        try {
            int xLogin = 328;
            int yLogin = 258;
            InfoController login = (InfoController) replaceSceneContent("/fxml/Register.fxml", xLogin, yLogin);
            login.setApp(this);
            Scene scene = stage.getScene();
            scene.getStylesheets().add("/styles/Settings.css");
            stage.setMinWidth(stage.getWidth());
            stage.setMinHeight(stage.getHeight());
            // запрет изменения размера окна
            stage.setResizable(false);
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoGeneral() {
        try {
            GeneralController login = (GeneralController) replaceSceneContent("/fxml/General.fxml", 228, 291);
            login.setApp(this);
            Scene scene = stage.getScene();
            scene.getStylesheets().add("/styles/General.css");
        } catch (Exception ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml, int width, int height) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        }

        FadeTransition ft = new FadeTransition(Duration.millis(3000), page);
        ft.setFromValue(0.0);
        ft.setToValue(1.0);
        ft.play();
        Scene scene = new Scene(page, width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        double x = lx / 2 - width / 2;
        double y = ly / 2 - height / 2;

        stage.setX(x);
        stage.setY(y);

        return (Initializable) loader.getController();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }   
    
    Association tempAss = null;
    public Association getTempAss(){
    return tempAss;}
    
    public void setTempAss(Association tempAss){
        this.tempAss = tempAss;
    }
    
    List<Association> listAssociations = null;
    
    public void updateListAss() throws SQLException{
        listAssociations = Factory.getInstance().getAssociationDAO().getAllAssociationsByUser(user);
    }
    
    public List<Association> getListAss(){
        return listAssociations;
    }
    
    
    public boolean userLogging(String userId, String password) {
        User tempUser = auth.validate(userId, password);
        System.out.println("userLogging user.getNameDB() = " + tempUser.getLoginUser());
        if (tempUser != null) {
            setUser(tempUser);
            gotoGeneral();
            return true;
        } else {
            return false;
        }
    }

    public void openRegisters() {
        gotoRegisters();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
        HibernateUtil.shutdown();
        System.exit(0);
    }

}
