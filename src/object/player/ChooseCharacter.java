package object.player;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import object.card.CardImage;
import object.card.CharacterCard;
import object.card.RoleCard;
import object.exception.InvalidIDException;

import java.io.IOException;
import java.util.List;

public class ChooseCharacter {

	private final RoleCard roleCard;
	private final List<CharacterCard> characterCards;
	private final Stage stage;

	private CharacterCard chosenCharacter = null;

	private Pane scenesRoot;

	@FXML
	private Pane root;

	@FXML
	private VBox charactersPane;

	@FXML
	private GridPane rolePane;
	private ToggleGroup characterRadioButtons = new ToggleGroup();

	public ChooseCharacter(RoleCard roleCard, List<CharacterCard> characterCards, Stage stage, Pane root) {
		this.roleCard = roleCard;
		this.characterCards = characterCards;
		this.stage = stage;
		this.scenesRoot = root;
	}

	@FXML
	void cancel(ActionEvent event) throws IOException {
		stage.close();
	}

	@FXML
	void chooseCharacter(ActionEvent event) throws InvalidIDException {
		if (characterRadioButtons.getSelectedToggle() instanceof RadioButton) {
			RadioButton selected = (RadioButton) characterRadioButtons.getSelectedToggle();
			chosenCharacter = new CharacterCard(Integer.parseInt(selected.getId()));
			stage.close();
		}

	}

	@FXML
	void initialize() {
		root.prefHeightProperty().bind(scenesRoot.heightProperty());
		for (CharacterCard card : characterCards) {
			RadioButton radioButton = new RadioButton();
			CardImage iv = card.getCardImage(true);
			radioButton.setGraphic(iv);
			radioButton.setId(card.getID() + "");
			radioButton.setToggleGroup(characterRadioButtons);
			charactersPane.getChildren().add(radioButton);
			iv.setZoomToNode(radioButton);
			iv.fitHeightProperty().bind(charactersPane.heightProperty().divide(characterCards.size()).subtract((20)));
		}
		CardImage iv = roleCard.getCardImage(true);
		iv.setFitHeight(340);
		iv.setFaceUp(true);
		rolePane.getChildren().add(iv);
		iv.fitHeightProperty().bind(rolePane.heightProperty().multiply(0.99));
	}

	public CharacterCard getChosenCharacter() {
		return chosenCharacter;
	}
}
