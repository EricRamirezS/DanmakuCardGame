package controller;

import controller.game.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import object.scene.MainScene;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class MainScreen {

    @FXML
    private Pane root;

    @FXML
    void doCardList(ActionEvent event) {

    }

    @FXML
    void doConfiguration(ActionEvent event) {

    }

    @FXML
    void doCredits(ActionEvent event) {

    }

    @FXML
    void doNetPlay(ActionEvent event) {

    }

    @FXML
    void doSinglePlayer(ActionEvent event) throws IOException {
        MainScene.setScene(FXMLLoader.load(getClass().getResource("/controller/GameSetup.fxml")),0,42,0,42);
    }

    @FXML
    void goToDanmaku(ActionEvent event) {
        try {
            Desktop desktop = java.awt.Desktop.getDesktop();
            URI oURL = new URI("http://danmaku.party");
            desktop.browse(oURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        GameController.gameOnGoing = false;
    }
}
