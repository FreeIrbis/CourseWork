package com.quasar.hibernateh2.controller;

import com.quasar.hibernateh2.app.MainApp;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Artur
 */
public abstract class AbstractController extends AnchorPane {

    protected MainApp application;

    public void setApp(MainApp application) {
        this.application = application;
    }
}
