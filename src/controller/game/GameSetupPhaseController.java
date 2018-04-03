package controller.game;

import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import object.card.*;
import object.exception.InvalidIDException;
import object.player.BotPlayer;
import object.player.HumanPlayer;
import object.player.Player;
import object.scene.MainScene;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 13-03-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class GameSetupPhaseController {

	private volatile boolean done = true;
	private GameController gameController;

	GameSetupPhaseController(GameController gc) {
		gameController = gc;
	}

	void shuffleDecks() {
		gameController.shuffleLEDeckCard(false);
		gameController.shuffleMainDeck(false);
		gameController.shuffleIncidentDeck(false);
		gameController.shuffleCharacterDeck();
	}

	private void setRoleDeck(List<Integer> roles) {
		for (Integer i : roles) {
			try {
				gameController.getRoleCards().add(new RoleCard(i));
			} catch (InvalidIDException e) {
				System.err.println("Unexpected error creating Role Card ID: " + i);
				e.printStackTrace();
			}
		}
	}

	private void setRoleDeck(int nOfPlayers) {

		//Creating separated list for Stage Boss, Partner and Ex Boss
		List<Integer> possiblesPartnerID = new ArrayList<>();
		List<Integer> possiblesStageID = new ArrayList<>();
		List<Integer> possiblesExID = new ArrayList<>();
		//System.out.println("possible role list created");
		//Adding playable Stage Bosses role
		possiblesExID.add(13);
		possiblesExID.add(15);
		if (gameController.isLunaticExtra()) {
			possiblesExID.add(17);
			possiblesExID.add(19);
		}
		//System.out.println("Set possible EX bosses");

		//Adding Playable Roles base on the number of players
		switch (nOfPlayers) {
			case 8:
			case 7:
				//Adding One True Partner Role
				possiblesPartnerID.add(12);
			case 6: //Jump to 5
			case 5:
				//Adding Partners and EX Midboss
				possiblesPartnerID.add(9);
				possiblesPartnerID.add(10);
				possiblesPartnerID.add(11);
				//Adding Anti-Heroine, Challenger and Final Boss
				possiblesStageID.add(6);
				possiblesStageID.add(7);
				possiblesStageID.add(8);
			case 4:
				//Adding StageBosses
				possiblesStageID.add(3);
				possiblesStageID.add(4);
				possiblesStageID.add(5);
		}
		//System.out.println("Added possible Stage and Partners");

		// Adding Heroine to Role Deck
		Collections.shuffle(possiblesExID);
		Collections.shuffle(possiblesPartnerID);
		Collections.shuffle(possiblesStageID);
		//System.out.println("Possible Roles shuffled");

		ObservableList<RoleCard> roleCards = gameController.getRoleCards();
		switch (nOfPlayers) {
			case 8://Adding the Rival Role to the Role Deck
				try {
					roleCards.add(new RoleCard(2));
					//System.out.println("Rival Role card Created and added");
				} catch (InvalidIDException e) {
					System.err.println("Unexpected error creating Role Card ID: 2");
					e.printStackTrace();
				}
			case 7: //Adding a Partner Role to the Role Deck
				try {
					roleCards.add(new RoleCard(possiblesPartnerID.get(1)));
					//System.out.println("A partner Role Card created and added");
				} catch (InvalidIDException e) {
					System.err.println("Unexpected error creating Role Cards ");
					e.printStackTrace();
				}
			case 6: //Adding a Stage Boss Role to the Role Deck
				try {
					roleCards.add(new RoleCard(possiblesStageID.get(2)));
					//System.out.println("A Stage Boss Role Card created and added");
				} catch (InvalidIDException e) {
					System.err.println("Unexpected error creating Role Cards ");
					e.printStackTrace();
				}
			case 5: //Adding a Partner Role to the Role Deck
				try {
					roleCards.add(new RoleCard(possiblesPartnerID.get(0)));
					//System.out.println("A partner Role Card created and added");
				} catch (InvalidIDException e) {
					System.err.println("Unexpected error creating Role Cards ");
					e.printStackTrace();
				}
			case 4: //Adding 2 Stage Bosses, 1 EX Boss and the Heroine Role to the Role Deck
				try {
					roleCards.add(new RoleCard(possiblesStageID.get(0)));
					roleCards.add(new RoleCard(possiblesStageID.get(1)));
					roleCards.add(new RoleCard(possiblesExID.get(0)));
					roleCards.add(new RoleCard(1));
					//System.out.println("A Stage boss Role card created and added");
					//System.out.println("A Stage boss Role card id created and added");
					//System.out.println("A EX Boss Role card created and added");
					//System.out.println("Heroine Role card created and added");
				} catch (InvalidIDException e) {
					System.err.println("Unexpected error creating Role Cards ");
					e.printStackTrace();
				}
		}
	}

	private void setDecks(boolean banLilyWhite) {
		//System.out.println("creating Decks");
		for (int i = 0; i < (gameController.isLunaticExtra() ? 100 : 80); i++) {
			try {
				gameController.getMainDeckList().add(new DeckCard(i + 1));
				//System.out.println("Deck card id: " + (i + 1) + " created and added");
			} catch (InvalidIDException e) {
				System.err.println("Unexpected error creating Deck Card ID: " + i);
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
				//System.out.println("Unexpected Error");
			}
		}
		for (int i = 0; i < (gameController.isLunaticExtra() ? 40 : 0); i++) {
			try {
				gameController.getLeDeckList().add(new LunaticExtraCard(i + 1));
				//System.out.println("LE card id: " + (i + 1) + " created and added");
			} catch (InvalidIDException e) {
				System.err.println("Unexpected error creating lunatic Extra Card ID: " + i);
				e.printStackTrace();
			}
		}
		for (int i = 0; i < (gameController.isLunaticExtra() ? 20 : 16); i++) {
			try {
				if (banLilyWhite && i + 1 == 8) continue;
				gameController.getIncidentDeckList().add(new IncidentCard(i + 1));
				//System.out.println("Incident card id: " + (i + 1) + " created and added");
			} catch (InvalidIDException e) {
				System.err.println("Unexpected error creating Incident Card ID: " + i);
				e.printStackTrace();
			}
		}
		for (int i = 0; i < (gameController.isLunaticExtra() ? 48 : 24); i++) {
			try {
				gameController.getCharacterDeck().add(new CharacterCard(i + 1));
				//System.out.println("Character card id: " + (i + 1) + " created and added");
			} catch (InvalidIDException e) {
				System.err.println("Unexpected error creating Character Card ID: " + i);
				e.printStackTrace();
			}
		}
	}

	void setDecks(boolean banLilyWhite, List<Integer> roles) {
		setRoleDeck(roles);
		setDecks(banLilyWhite);
	}

	void setDecks(boolean banLilyWhite, int nOfPlayers) {
		setRoleDeck(nOfPlayers);
		setDecks(banLilyWhite);
	}

	void setPlayers() {
		GameController.players.clear();
		gameController.shuffleRoleDeck();
		try {
			GameController.players.add(new HumanPlayer(gameController.getRoleCards().get(0)));
			for (int i = 1; i < gameController.getRoleCards().size(); i++) {
				GameController.players.add(new BotPlayer(gameController.getRoleCards().get(i)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	boolean setPlayersCharacters(int charactersToDeal) {
		for (Player player : GameController.players) {
			List<CharacterCard> charactersToChoose = new ArrayList<>();
			for (CharacterCard character : gameController.getCharacterDeck()) {
				charactersToChoose.add(character);
				if (charactersToChoose.size() >= charactersToDeal)
					break;
			}
			for (CharacterCard character : charactersToChoose) {
				gameController.getCharacterDeck().remove(character);
			}
			List<CharacterCard> discardedCharacter = player.chooseCharacter(charactersToChoose);
			if (discardedCharacter == null) {
				try {
					MainScene.setScene(FXMLLoader.load(getClass().getResource("/controller/GameSetup.fxml")), 0, 42, 0, 42);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
			for (CharacterCard character : discardedCharacter) {
				gameController.getCharacterDiscardPile().add(character);
			}
		}
		return true;
	}

	void drawStartingHand() {
		List<Player> players = gameController.getTurnController().getPlayersByTurn();
		drawCards(players, 0);
	}

	private void drawCards(@NotNull List<Player> players, int i) {
		if (i >= players.size()) {
			startOfTheTurnEffect();
		} else {
			done = false;
			Timeline tl = gameController.getAnimationController().getDrawCardAnimation(gameController.getTableController().getMainDeckGroup(), players.get(i));
			tl.setOnFinished(e -> {
				if (players.get(i).getMaxHandSize() > players.get(i).getHand().size()) {
					drawCards(players, i);
				} else {
					drawCards(players, i + 1);
				}
			});
			tl.play();
		}
	}

	void startOfTheTurnEffect() {
		/* TODO Start of the turn effects only applies for Lunatic Extra Characters
		for (Player player : gameController.getTurnController().getPlayersByTurn()) {
			switch (player.getCharacterCard().getID()) {
				case CharacterCard.FUTATSUIWA_MAMIZOU: //Mamizou
					break;
				case CharacterCard.HOUJUU_NUE: //Nue
					break;
				case CharacterCard.KIJIN_SEIJA: //Seija
					break;
				case CharacterCard.YAKUMO_RAN: //Ran
			}
		}
		 */
		turnZero();
	}

	void turnZero() {
		//TODO turn Zero

		heroineFirstTurn();
	}

	void heroineFirstTurn() {
		gameController.getPhaseControlller().start();
	}
}
