package object.player;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import object.card.CharacterCard;
import object.card.RoleCard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 26-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class HumanPlayer extends Player {

	public HumanPlayer(RoleCard roleCard) throws IOException {
		super(roleCard);
	}

	@Override
	public void playCard() {
		//TODO
	}

	@Override
	public void discardCard() {
		//TODO
	}

	@Override
	public void buyLECard() {
		//TODO
	}

	@Override
	public List<CharacterCard> recruitCharacter(@NotNull CharacterCard[] characters) {

		return null;
	}

	@Override
	@Nullable
	public List<CharacterCard> chooseCharacter(@NotNull List<CharacterCard> characters) {
		Stage stage = new Stage();
		try {
			AnchorPane root = new AnchorPane();
			root.getStylesheets().addAll(
					"css/combo-box.css",
					"css/spinner.css",
					"css/radio-button.css",
					"css/check-box.css",
					"css/tab-pane.css",
					"css/button.css",
					"css/custom-id.css"
			);
			stage.setScene(new Scene(root, 1024, 768, true));

			ChooseCharacter controller = new ChooseCharacter(getRoleCard(), characters, stage, root);
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setController(controller);
			fxmlLoader.setLocation(getClass().getResource("/object/player/ChooseCharacter.fxml"));
			Pane pane = fxmlLoader.load();
			root.getChildren().add(pane);
			AnchorPane.setTopAnchor(pane, 0D);
			AnchorPane.setBottomAnchor(pane, 0D);
			AnchorPane.setLeftAnchor(pane, 0D);
			AnchorPane.setRightAnchor(pane, 0D);
			stage.setMaximized(true);
			stage.setMinWidth(640);
			stage.setMinHeight(480);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.showAndWait();
			CharacterCard chosenCharacter = controller.getChosenCharacter();
			if (chosenCharacter != null) {
				this.setCharacterCard(chosenCharacter);
				for (CharacterCard character : characters) {
					if (character.getID() == chosenCharacter.getID()) {
						characters.remove(character);
						break;
					}
				}
				return characters;
			} else return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
