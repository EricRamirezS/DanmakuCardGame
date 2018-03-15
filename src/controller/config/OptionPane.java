package controller.config;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import object.scene.MainScene;

import java.io.*;
import java.util.Properties;

public class OptionPane {

    @FXML
    private StackPane root;

    @FXML
    private Slider musicVolumeSlider;

    @FXML
    private Slider soundVolumeSlider;

    @FXML
    void goToMainMenu(ActionEvent event) throws IOException {
        MainScene.setScene(FXMLLoader.load(getClass().getResource("/controller/MainScreen.fxml")), 0, 42, 0, 42);
        MainScene.showingInGameOption = false;
    }

    @FXML
    void resumeGame(ActionEvent event) {
        ((Pane)root.getScene().getRoot()).getChildren().remove(root);
    }

    @FXML
    void initialize() {
        Properties prop = new Properties();
        try {
            try {
                prop.load(getClass().getResourceAsStream("/property/config/configurations.properties"));
                System.out.println(prop.getProperty("BGM"));
                //musicVolumeSlider.setValue(Double.parseDouble(prop.getProperty("BGM")));
                //soundVolumeSlider.setValue(Double.parseDouble(prop.getProperty("SFX")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            musicVolumeSlider.valueProperty().addListener((observableValue,oldValue,newValue)->{
                //setVolume()
                prop.setProperty("BGM",newValue.toString());
                System.out.println(newValue);
                try {
                    OutputStream output = new FileOutputStream(getClass().getResource("/property/config/configurations.properties").getFile());
                    prop.store(output, "Music volume");
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            soundVolumeSlider.valueProperty().addListener((observableValue,oldValue,newValue)->{
                //setVolume()
                prop.setProperty("SFX",newValue.toString());
                System.out.println(newValue);
                try {
                    OutputStream output = new FileOutputStream(getClass().getResource("/property/config/configurations.properties").getFile());
                    prop.store(output, "Sound Effect volume");
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}