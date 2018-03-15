package controller;

import controller.game.GameController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import object.scene.MainScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameSetup {

    @FXML
    public CheckBox cxbBanLilyWhite;

    @FXML
    public CheckBox cxbHeroine;
    IntegerProperty numberOfCharacterCard = new SimpleIntegerProperty(24);
    ObservableList<Integer> playerChoiceOption = FXCollections.observableArrayList();
    @FXML
    private Pane root;
    @FXML
    private ComboBox<Number> cbPlayer;
    @FXML
    private CheckBox cxbPartner1;
    @FXML
    private CheckBox cxbStageBoss1;
    @FXML
    private CheckBox cxbRival;
    @FXML
    private CheckBox cxbPartner2;
    @FXML
    private CheckBox cxvExMidboss;
    @FXML
    private CheckBox cxbOTP;
    @FXML
    private CheckBox cxbStageBoss2;
    @FXML
    private CheckBox cxbStageBoss3;
    @FXML
    private CheckBox cxbFinalBoss;
    @FXML
    private CheckBox cxbChallenger;
    @FXML
    private CheckBox cxbAntiHeroine;
    @FXML
    private CheckBox cxbLE;
    @FXML
    private CheckBox cxbRandomRoles;
    @FXML
    private RadioButton rbExBoss;
    @FXML
    private RadioButton rbPhantasmBoss;
    @FXML
    private RadioButton rbSecretBoss;
    @FXML
    private RadioButton rbLoneWolf;
    @FXML
    private Button btnStart;
    @FXML
    private ToggleGroup exBossButtonGroup;
    @FXML
    private Spinner<Integer> playerChoiceSpinner;
    @FXML
    private RadioButton standartGameRB;
    @FXML
    private ToggleGroup gameModeButtonGroup;
    @FXML
    private RadioButton playersChoiceRB;
    @FXML
    private RadioButton doubleThreatRB;
    @FXML
    private RadioButton characterDraftRB;
    @FXML
    private Text textPlayerChoice;
    private int partnerMaxSize = 0;
    private ArrayList<CheckBox> partnerList = new ArrayList<>();
    private int stageBossMaxSize = 2;
    private ArrayList<CheckBox> stageList = new ArrayList<>();
    private BooleanProperty roleAmountProperty = new SimpleBooleanProperty(true);

    private void countRoles() {
        if (cxbRandomRoles.isSelected()) roleAmountProperty.setValue(true);
        else {
            int selectedRoles = 2; //Heroine and a EX Boss always selected by default
            selectedRoles += cxbRival.isSelected() ? 1 : 0;
            for (CheckBox checkbox : partnerList) selectedRoles += checkbox.isSelected() ? 1 : 0;
            for (CheckBox checkbox : stageList) selectedRoles += checkbox.isSelected() ? 1 : 0;
            roleAmountProperty.setValue(selectedRoles == cbPlayer.getValue().intValue());
        }
    }

    @FXML
    void leChange(ActionEvent event) {
        if (!cxbLE.isSelected()) {
            if (rbLoneWolf.isSelected() || rbSecretBoss.isSelected()) rbExBoss.setSelected(true);
        }
        numberOfCharacterCard.setValue(cxbLE.isSelected() ? 48 : 24);
    }

    @FXML
    void partnerChange(ActionEvent event) {
        unselectedDisableOrExceeded(partnerList, partnerMaxSize);
        countRoles();
    }

    @FXML
    void playersChange(ActionEvent event) {
        int nOfPlayers = cbPlayer.getValue().intValue();
        if (nOfPlayers > 4) partnerMaxSize = 1;
        else partnerMaxSize = 0;
        if (nOfPlayers > 5) stageBossMaxSize = 3;
        else stageBossMaxSize = 2;
        if (nOfPlayers > 6) partnerMaxSize = 2;
        unselectedDisableOrExceeded(partnerList, partnerMaxSize);
        unselectedDisableOrExceeded(stageList, stageBossMaxSize);
        countRoles();
    }

    private void unselectedDisableOrExceeded(ArrayList<CheckBox> list, int limit) {
        int counter = 0;
        for (CheckBox checkBox : list) {
            if (checkBox.isDisable()) checkBox.setSelected(false);
            if (checkBox.isSelected()) counter++;
            if (counter > limit) checkBox.setSelected(false);
        }
    }

    @FXML
    void randomRoleChange(ActionEvent event) {
        countRoles();
    }

    @FXML
    void stageBossChange(ActionEvent event) {
        unselectedDisableOrExceeded(stageList, stageBossMaxSize);
        countRoles();
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        MainScene.setScene(FXMLLoader.load(getClass().getResource("/controller/MainScreen.fxml")), 0, 42, 0, 42);
    }

    @FXML
    void startGame(ActionEvent event) {
        if (cxbRandomRoles.isSelected()) {
            if (characterDraftRB.isSelected()) {
                //TODO when Character Draft mode is supported
            } else {
                int charactersToDeal = standartGameRB.isSelected() ? 2 :
                        (doubleThreatRB.isSelected() ? 4 : playerChoiceSpinner.getValue());
                new GameController(
                        cbPlayer.getSelectionModel().getSelectedItem().intValue(),
                        cxbLE.isSelected(),
                        cxbBanLilyWhite.isSelected(), charactersToDeal);
            }
        } else {
            List<Integer> roleIDs = new ArrayList<>();
            roleIDs.add(1);
            if (cxbRival.isSelected()) roleIDs.add(8);
            roleIDs.add(Integer.parseInt(((RadioButton) exBossButtonGroup.getSelectedToggle()).getId()));
            for (CheckBox checkBox : partnerList) {
                if (checkBox.isSelected()) roleIDs.add(Integer.parseInt(checkBox.getId()));
            }
            for (CheckBox checkBox : stageList) {
                if (checkBox.isSelected()) roleIDs.add(Integer.parseInt(checkBox.getId()));
            }
            if (characterDraftRB.isSelected()) {
                //TODO when Character Draft mode is supported
            } else {
                int charactersToDeal = standartGameRB.isSelected() ? 2 :
                        (doubleThreatRB.isSelected() ? 4 : playerChoiceSpinner.getValue());
                new GameController(
                        cbPlayer.getSelectionModel().getSelectedItem().intValue(),
                        cxbLE.isSelected(),
                        cxbBanLilyWhite.isSelected(), charactersToDeal);
            }
        }
    }

    @FXML
    void initialize() {
        partnerList.add(cxbPartner1);
        partnerList.add(cxbPartner2);
        partnerList.add(cxvExMidboss);
        partnerList.add(cxbOTP);
        stageList.add(cxbStageBoss1);
        stageList.add(cxbStageBoss2);
        stageList.add(cxbStageBoss3);
        stageList.add(cxbFinalBoss);
        stageList.add(cxbChallenger);
        stageList.add(cxbAntiHeroine);
        playerChoiceSpinner.visibleProperty().bind(playersChoiceRB.selectedProperty());
        textPlayerChoice.visibleProperty().bind(playersChoiceRB.selectedProperty());
        rbPhantasmBoss.disableProperty().bind(cxbRandomRoles.selectedProperty());
        rbExBoss.disableProperty().bind(cxbRandomRoles.selectedProperty());
        //TODO Uncomment when LE supported
        //rbLoneWolf.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cxbLE.selectedProperty().not()));
        //rbSecretBoss.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cxbLE.selectedProperty().not()));
        cxbRival.selectedProperty().bind(cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(8));
        cxbPartner1.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(4)));
        cxbPartner2.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(4)));
        cxvExMidboss.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(4)));
        BooleanProperty player7 = new SimpleBooleanProperty(false);
        player7.bind(Bindings.or(cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(7), cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(8)));
        cxbOTP.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), player7.not()));
        cxbStageBoss1.disableProperty().bind(cxbRandomRoles.selectedProperty());
        cxbStageBoss2.disableProperty().bind(cxbRandomRoles.selectedProperty());
        cxbStageBoss3.disableProperty().bind(cxbRandomRoles.selectedProperty());
        cxbFinalBoss.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(4)));
        cxbChallenger.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(4)));
        cxbAntiHeroine.disableProperty().bind(Bindings.or(cxbRandomRoles.selectedProperty(), cbPlayer.getSelectionModel().selectedItemProperty().isEqualTo(4)));

        cbPlayer.setItems(FXCollections.observableArrayList(4, 5, 6, 7, 8));
        cbPlayer.getSelectionModel().select(new Integer(4));
        btnStart.disableProperty().bind(roleAmountProperty.not());
        SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 6);
        valueFactory.setValue(2);
        cbPlayer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            valueFactory.setMax((int) Math.floor((cxbLE.isSelected() ? 48 : 24) / newValue.intValue()));
        });
        playerChoiceSpinner.setValueFactory(valueFactory);
    }

}
