/*
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 26-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
package controller.game;

import controller.misc.FXUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import object.card.Card;
import object.card.CharacterCard;
import object.card.RoleCard;
import object.player.Player;
import object.scene.MainScene;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class GameController {

	public static boolean gameOnGoing = false;

	public static ObservableList<Player> players = FXCollections.observableArrayList();
	private final ObservableList<Card> mainDeck = FXCollections.observableArrayList();
	private final ObservableList<RoleCard> roleCards = FXCollections.observableArrayList();
	private final ObservableList<Card> mainDiscardPile = FXCollections.observableArrayList();
	private final ObservableList<Card> leDeck = FXCollections.observableArrayList();
	private final ObservableList<Card> leDiscardPile = FXCollections.observableArrayList();
	private final ObservableList<Card> incidentDeck = FXCollections.observableArrayList();
	private final ObservableList<Card> pastIncidentPile = FXCollections.observableArrayList();
	private final ObservableList<Card> currentIncidents = FXCollections.observableArrayList();
	private final ObservableList<ObservableList<Card>> collectedCards = FXCollections.observableArrayList();
	private final ObservableList<CharacterCard> characterDeck = FXCollections.observableArrayList();
	private final ObservableList<CharacterCard> characterDiscardPile = FXCollections.observableArrayList();
	private final boolean lunaticExtra;

	private final TableController tableController = new TableController(this);
	private final PhaseController phaseControlller = new PhaseController(this);
	private final GameSetupPhaseController gameSetup = new GameSetupPhaseController(this);
	private final AnimationController animationController = new AnimationController(this);
	private TurnController turnController;
	private Pane parent;

	public GameController(final List<Integer> selectedRoles, final boolean le, final boolean banLilyWhite, final int charactersToDeal) {
		try {
			MainScene.setScene(FXMLLoader.load(getClass().getResource("/controller/misc/LoadingScreen.fxml")), 0, 50, 0, 50);
		} catch (IOException e) {
			e.printStackTrace();
		}

		lunaticExtra = le;

		Task<Void> task = new Task<Void>() {
			@Nullable
			@Override
			protected Void call() {
				gameSetup.setDecks(banLilyWhite, selectedRoles);
				gameSetup.shuffleDecks();
				gameSetup.setPlayers();
				return null;
			}
		};
		task.setOnSucceeded(e -> {
			turnController = new TurnController(this);
			if (gameSetup.setPlayersCharacters(charactersToDeal)) {
				try {
					setView();
					gameSetup.drawStartingHand();
				} catch (ExecutionException | InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
	}

	public GameController(final int nOfPlayers, final boolean le, final boolean banLilyWhite, final int charactersToDeal) {
		try {
			MainScene.setScene(FXMLLoader.load(getClass().getResource("/controller/misc/LoadingScreen.fxml")), 0, 50, 0, 50);
		} catch (IOException e) {
			e.printStackTrace();
		}
		lunaticExtra = le;
		Task<Void> task = new Task<Void>() {
			@Nullable
			@Override
			protected Void call() {
				gameSetup.setDecks(banLilyWhite, nOfPlayers);
				gameSetup.shuffleDecks();
				gameSetup.setPlayers();
				return null;
			}
		};
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		task.setOnSucceeded(e -> {
			turnController = new TurnController(this);
			if (gameSetup.setPlayersCharacters(charactersToDeal)) {
				try {
					setView();
					gameSetup.drawStartingHand();
				} catch (ExecutionException | InterruptedException ex) {
					ex.printStackTrace();
				}
			}
		});
		thread.start();
	}

	@Contract(pure = true)
	public static ObservableList<Player> getPlayers() {
		return players;
	}

	private void setView() throws ExecutionException, InterruptedException {
		FXUtilities.runAndWait(() -> {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setController(tableController);
			fxmlLoader.setLocation(getClass().getResource("/controller/game/Table.fxml"));
			try {
				parent = fxmlLoader.load();
				MainScene.setScene(parent);
				gameOnGoing = true;
				tableController.placePlayerBoards();
				Thread.sleep(10);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Shuffles a Deck
	 *
	 * @param deck     The deck to Shuffle.
	 * @param animated If true, Shows an animation shuffling the deck
	 */
	public void shuffleDeck(ObservableList<Card> deck, boolean animated) {
		Collections.shuffle(deck);
		if (animated) {
			//TODO animation
		}
	}

	/**
	 * Shuffles the Main Deck
	 *
	 * @param animated If true, Shows an animation shuffling the deck
	 */
	public void shuffleMainDeck(boolean animated) {
		//System.out.println("Shuffling Main Deck");
		Collections.shuffle(mainDeck);
		if (animated) {
			//TODO animation
		}
		//System.out.println("Main Deck Shuffled");
	}

	/**
	 * Shuffles the Lunatic Extra Deck
	 *
	 * @param animated if true, Shows an animation shuffling the deck
	 */
	public void shuffleLEDeckCard(boolean animated) {
		//System.out.println("Shuffling Lunatic Extra Deck");
		Collections.shuffle(leDeck);
		if (animated) {
			//TODO animation
		}
		//System.out.println("Lunatic Extra Deck Shuffled");
	}

	/**
	 * Shuffles the Incident Deck
	 *
	 * @param animated if true, Shows an animation shuffling the deck
	 */
	public void shuffleIncidentDeck(boolean animated) {
		//System.out.println("Shuffling Incident Deck");
		Collections.shuffle(incidentDeck);
		if (animated) {
			//TODO animation
		}
		//System.out.println("Incident Deck Shuffled");
	}

	/**
	 * Shuffles the Role Deck
	 */
	public void shuffleRoleDeck() {
		//System.out.println("Shuffling Role Deck");
		//System.out.println("Role Deck Shuffled");
		Collections.shuffle(roleCards);
	}

	/**
	 * Shuffles the Character Deck
	 */
	public void shuffleCharacterDeck() {
		Collections.shuffle(characterDeck);
	}

	public ObservableList<Card> getMainDeckList() {
		return mainDeck;
	}

	public ObservableList<Card> getMainDiscardPileList() {
		return mainDiscardPile;
	}

	public ObservableList<Card> getLeDeckList() {
		return leDeck;
	}

	public ObservableList<Card> getLeDiscardPileList() {
		return leDiscardPile;
	}

	public ObservableList<Card> getIncidentDeckList() {
		return incidentDeck;
	}

	public ObservableList<Card> getPastIncidentPileList() {
		return pastIncidentPile;
	}

	public ObservableList<Card> getCurrentIncidentsList() {
		return currentIncidents;
	}

	ObservableList<RoleCard> getRoleCards() {
		return roleCards;
	}

	public ObservableList<ObservableList<Card>> getCollectedCards() {
		return collectedCards;
	}

	public TableController getTableController() {
		return tableController;
	}

	public boolean isLunaticExtra() {
		return lunaticExtra;
	}

	public ObservableList<CharacterCard> getCharacterDeck() {
		return characterDeck;
	}

	public ObservableList<CharacterCard> getCharacterDiscardPile() {
		return characterDiscardPile;
	}

	Pane getParent() {
		return parent;
	}

	TurnController getTurnController() {
		return turnController;
	}

	AnimationController getAnimationController() {
		return animationController;
	}
}
