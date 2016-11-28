package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.app.MainApp;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Artur
 */
public abstract class AbstractController extends AnchorPane {

    protected static MainApp application;

    public void setApp(MainApp application) {
        AbstractController.application = application;
    }
    
    public MainApp getApp() {
        return application;
    }
}
