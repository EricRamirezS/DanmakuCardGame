package object.player;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import object.card.Card;
import object.card.CharacterCard;
import object.card.Deck;
import object.card.RoleCard;
import object.scene.Pos;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 21-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public abstract class Player {

	//region Variable Declaration
	private final IntegerProperty life = new SimpleIntegerProperty(4);
	private final IntegerProperty maxLife = new SimpleIntegerProperty(4);
	private final IntegerProperty danmakuCount = new SimpleIntegerProperty(0);
	private final IntegerProperty danmakuLimit = new SimpleIntegerProperty(1);
	private final IntegerProperty maxHandSize = new SimpleIntegerProperty(4);
	private final BooleanProperty danmakuPlayed = new SimpleBooleanProperty(false);
	private final BooleanProperty spellcardPlayed = new SimpleBooleanProperty(false);
	private final IntegerProperty range = new SimpleIntegerProperty(1);
	private final IntegerProperty distanceBonus = new SimpleIntegerProperty(0);
	private final BooleanProperty spellcardAvailable = new SimpleBooleanProperty(true);
	private final BooleanProperty abilityAvailable = new SimpleBooleanProperty(true);
	private final BooleanProperty itemsAvailable = new SimpleBooleanProperty(true);

	private final SimpleObjectProperty<RoleCard> roleCard = new SimpleObjectProperty<>();
	private final SimpleObjectProperty<CharacterCard> characterCard = new SimpleObjectProperty<>();

	private final ObservableList<Card> hand = FXCollections.observableArrayList();
	private final ObservableList<Card> items = FXCollections.observableArrayList();
	private final ObservableList<CharacterCard> extraCharacterCards = FXCollections.observableArrayList();

	private final Board boardPane;
	//endregion

	public Player(RoleCard roleCard) throws IOException {
		Board board = new Board(this);
		this.roleCard.addListener((observable, oldValue, newValue) -> {
			if (newValue.getID() == 1) {
				maxLife.setValue(5);
				life.setValue(life.get() + 1);
			}
		});
		this.items.addListener((ListChangeListener<Card>) c -> calculateBonus());
		this.characterCard.addListener((obsV, oldV, newV) -> calculateBonus());
		this.extraCharacterCards.addListener((ListChangeListener<Card>) c -> calculateBonus());
		FXMLLoader loader = new FXMLLoader();
		loader.setRoot(board);
		loader.setController(board);
		loader.setLocation(getClass().getResource("/object/player/board.fxml"));
		boardPane = loader.load();
		this.roleCard.setValue(roleCard);
		if (this.roleCard.get().getID() == 1) lifeProperty().set(5);
		calculateBonus();
	}

	//region Getters
	public ObservableList<Card> getHand() {
		return hand;
	}

	public ObservableList<Card> getItems() {
		return items;
	}

	public RoleCard getRoleCard() {
		return roleCard.getValue();
	}

	//region Setters
	public void setRoleCard(RoleCard roleCard) {
		this.roleCard.set(roleCard);
	}

	public CharacterCard getCharacterCard() {
		return characterCard.getValue();
	}

	public void setCharacterCard(CharacterCard characterCard) {
		this.characterCard.set(characterCard);
	}

	public ObservableList<CharacterCard> getExtraCharacterCards() {
		return extraCharacterCards;
	}

	public SimpleObjectProperty<RoleCard> roleCardProperty() {
		return roleCard;
	}

	public SimpleObjectProperty<CharacterCard> characterCardProperty() {
		return characterCard;
	}

	public Pane getBoardPane() {
		return boardPane;
	}

	public int getLife() {
		return life.get();
	}

	public IntegerProperty lifeProperty() {
		return life;
	}

	public int getMaxLife() {
		return maxLife.get();
	}

	public IntegerProperty maxLifeProperty() {
		return maxLife;
	}

	public int getDanmakuCount() {
		return danmakuCount.get();
	}

	public IntegerProperty danmakuCountProperty() {
		return danmakuCount;
	}

	public int getDanmakuLimit() {
		return danmakuLimit.get();
	}

	public IntegerProperty danmakuLimitProperty() {
		return danmakuLimit;
	}

	public boolean isDanmakuPlayed() {
		return danmakuPlayed.get();
	}

	public BooleanProperty danmakuPlayedProperty() {
		return danmakuPlayed;
	}

	public boolean isSpellcardPlayed() {
		return spellcardPlayed.get();
	}

	public BooleanProperty spellcardPlayedProperty() {
		return spellcardPlayed;
	}

	public int getMaxHandSize() {
		return maxHandSize.get();
	}

	public IntegerProperty maxHandSizeProperty() {
		return maxHandSize;
	}

	public int getRange() {
		return range.get();
	}

	public IntegerProperty rangeProperty() {
		return range;
	}

	public int getDistanceBonus() {
		return distanceBonus.get();
	}

	public IntegerProperty distanceBonusProperty() {
		return distanceBonus;
	}

	public boolean isSpellcardAvailable() {
		return spellcardAvailable.get();
	}

	public BooleanProperty spellcardAvailableProperty() {
		return spellcardAvailable;
	}

	public boolean isAbilityAvailable() {
		return abilityAvailable.get();
	}

	public BooleanProperty abilityAvailableProperty() {
		return abilityAvailable;
	}

	//endregion

	public boolean isItemsAvailable() {
		return itemsAvailable.get();
	}

	public BooleanProperty itemsAvailableProperty() {
		return itemsAvailable;
	}
	//endregion

	//region private Methods
	private void calculateBonus() {
		if (characterCard.getValue() != null) {
			int dl = 1;
			int r = 1;
			int db = 0;
			int mh = 4;
			if (itemsAvailable.get()) {
				for (Card card : items) {
					switch (card.getType()) {
						case DECK:
							switch (card.getID()) {
								case 10:
									db += 2;
									break;
								case 11:
									db += 2;
									break;
								case 33:
									r += 3;
									break;
								case 35:
									r += 1;
									dl += 1;
									break;
								case 36:
									r += 1;
									dl += 1;
									break;
								case 37:
									r += 1;
									dl += 1;
									break;
								case 38:
									r += 1;
									dl += 1;
									break;
								case 39:
									r += 1;
									dl += 1;
									break;
								case 40:
									r += 1;
									dl += 1;
									break;
								case 69:
									mh = 7;
									break;
								case 76:
									db += 1;
									dl += 2;
									break;
								case 90:
									db += 1;
									break;
								case 91:
									db += 1;
							}
							break;
						case LE:
							switch (card.getID()) {
								case 9:
									db += 2;
									break;
								case 10:
									r += 3;
									dl += 3;
									break;
								case 17:
									r += 1;
									break;
							}
					}
				}
			}
			mh = characterCard.get().getID() == 17 && abilityAvailable.get() && characterCard.get().isAbilityAvailable() ? 7 : mh;
			for (CharacterCard card : extraCharacterCards) {
				if (card.isAbilityAvailable() && abilityAvailable.get()) {
					int ID = card.getID();
					if (card.getID() == 37 && card.getCopyingCharacter() == null) {
						ID = card.getCopyingCharacter().getID();
					}
					switch (ID) {
						case 2:
							r += 2;
							break;
						case 7:
							r += 1;
							dl += 1;
							break;
						case 8:
							dl += 2;
							break;
						case 10:
							db += 1;
							break;
						case 17:
							mh = 7;
							break;
						case 28:
							if (life.get() == maxLife.get()) dl += 2;
							else if (life.get() > 1) r += 2;
							else db += 2;
							break;
						case 33:
							dl += 1;
						case 43:
							db += 1;
						case 48:
							r += 1;
					}
				}
			}
			if (abilityAvailable.get()) {
				int ID = characterCard.get().getID();
				if (characterCard.get().getID() == 37 && characterCard.get().getCopyingCharacter() == null) {
					ID = characterCard.get().getCopyingCharacter().getID();
				}
				switch (ID) {
					case 2:
						r += 2;
						break;
					case 7:
						r += 1;
						dl += 1;
						break;
					case 8:
						dl += 2;
						break;
					case 10:
						db += 1;
						break;
					case 17:
						mh = 7;
						break;
					case 28:
						if (life.get() == maxLife.get()) dl += 2;
						else if (life.get() > 1) r += 2;
						else db += 2;
						break;
					case 33:
						dl += 1;
					case 43:
						db += 1;
					case 48:
						r += 1;
				}
			}
			mh = roleCard.get().getID() == 1 || roleCard.get().getID() == 14 ? mh + 1 : mh;
			danmakuLimitProperty().set(dl);
			maxHandSize.set(mh);
			range.set(r);
			distanceBonus.set(db);
			maxLife.set(roleCard.get().getID() == 1 ||
					roleCard.get().getID() == 14 ||
					roleCard.get().getID() == 16 ||
					roleCard.get().getID() == 18 ||
					roleCard.get().getID() == 20
					? 5 : 4);
		}
	}
	//endregion

	public void addCardToHand(Card... cards) {
		hand.addAll(cards);
	}

	public void swapRoles(Player secondPlayer) {
		RoleCard tempCard = secondPlayer.getRoleCard();
		secondPlayer.setRoleCard(this.getRoleCard());
		this.setRoleCard(tempCard);
	}

	public void swapHand(Player secondPlayer) {
		ObservableList<Card> tempHand = FXCollections.observableArrayList(secondPlayer.getHand());
		secondPlayer.getHand().clear();
		for (Card card : hand) {
			secondPlayer.getHand().add(card);
		}
		hand.clear();
		for (Card card : tempHand) {
			hand.add(card);
		}
	}

	public Pos getCentralPos() {
		return boardPane.getCentralPos();
	}

	public Pos getExitPos() {
		return boardPane.getExitPos();
	}

	public void drawCard(Card card){
		hand.add(card);
	}

	/**
	 * resolve a card. link this function with the Stack Manager if
	 * the card is not instant
	 *
	 * @see object.game.StackManager
	 */
	public abstract void playCard();

	/**
	 * Discard a Card from the hand or item (if an effecto allows to)
	 */
	public abstract void discardCard();

	/**
	 * Allows the player to discard card in order to get a Lunatic Extra Card
	 */
	public abstract void buyLECard();

	/**
	 * Make the player choose a Character to recruit
	 *
	 * @param characters List of character available for the player to choose
	 * @return The characters that the player did not choose
	 */
	public abstract List<CharacterCard> recruitCharacter(@NotNull CharacterCard[] characters);

	/**
	 * Method that force the player to choose a Main Character
	 *
	 * @param characters List of character available for the player to choose
	 * @return The characters that the player did not choose
	 */
	@Nullable
	public abstract List<CharacterCard> chooseCharacter(@NotNull List<CharacterCard> characters);

}