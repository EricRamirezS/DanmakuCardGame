package object.player;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import object.card.*;
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
			int dl = 1; //danmaku limit
			int r = 1;  //range
			int db = 0; //distance bonus
			int mh = 4; //max hand
			if (itemsAvailable.get()) {
				for (Card card : items) {
					switch (card.getType()) {
						case DECK:
							switch (card.getID()) {
								case DeckCard.FOCUS_1:
									db += 2;
									break;
								case DeckCard.FOCUS_2:
									db += 2;
									break;
								case DeckCard.MINI_HACKKERO:
									r += 3;
									break;
								case DeckCard.POWER_1:
									r += 1;
									dl += 1;
									break;
								case DeckCard.POWER_2:
									r += 1;
									dl += 1;
									break;
								case DeckCard.POWER_3:
									r += 1;
									dl += 1;
									break;
								case DeckCard.POWER_4:
									r += 1;
									dl += 1;
									break;
								case DeckCard.POWER_5:
									r += 1;
									dl += 1;
									break;
								case DeckCard.POWER_6:
									r += 1;
									dl += 1;
									break;
								case DeckCard.SORCERERS_SUTRA_SCROLL:
									mh = 7;
									break;
								case DeckCard.STOPWATCH:
									db += 1;
									dl += 2;
									break;
								case DeckCard.NIMBLE_CLOTH_1:
									db += 1;
									break;
								case DeckCard.NIMBLE_CLOTH_2:
									db += 1;
							}
							break;
						case LE:
							switch (card.getID()) {
								case LunaticExtraCard.FOCUS:
									db += 2;
									break;
								case LunaticExtraCard.FULL_POWER:
									r += 3;
									dl += 3;
									break;
								case LunaticExtraCard.JEWELED_PAGODA:
									r += 1;
									break;
							}
					}
				}
			}
			for (CharacterCard card : extraCharacterCards) {
				if (card.isAbilityAvailable() && abilityAvailable.get()) {
					int ID = card.getID();
					if (card.getID() == CharacterCard.KOMEIJI_KOISHI && card.getCopyingCharacter() == null) {
						ID = card.getCopyingCharacter().getID();
					}
					switch (ID) {
						case CharacterCard.CIRNO:
							r += 2;
							break;
						case CharacterCard.IBUKI_SUIKA:
							r += 1;
							dl += 1;
							break;
						case CharacterCard.IZAYOI_SAKUYA:
							dl += 2;
							break;
						case CharacterCard.KAWASHIRO_NITORI:
							db += 1;
							break;
						case CharacterCard.PATCHOULI_KNOWLEDGE:
							mh = 7;
							break;
						case CharacterCard.HECATIA_LAPISLAZULI:
							if (life.get() == maxLife.get()) dl += 2;
							else if (life.get() > 1) r += 2;
							else db += 2;
							break;
						case CharacterCard.JUNKO:
							dl += 1;
						case CharacterCard.MORIYA_SUWAKO:
							db += 1;
						case CharacterCard.YASAKA_KANAKO:
							r += 1;
					}
				}
			}
			if (abilityAvailable.get()) {
				int ID = characterCard.get().getID();
				if (characterCard.get().getID() == CharacterCard.KOMEIJI_KOISHI && characterCard.get().getCopyingCharacter() == null) {
					ID = characterCard.get().getCopyingCharacter().getID();
				}
				switch (ID) {
					case CharacterCard.CIRNO:
						r += 2;
						break;
					case CharacterCard.IBUKI_SUIKA:
						r += 1;
						dl += 1;
						break;
					case CharacterCard.IZAYOI_SAKUYA:
						dl += 2;
						break;
					case CharacterCard.KAWASHIRO_NITORI:
						db += 1;
						break;
					case CharacterCard.PATCHOULI_KNOWLEDGE:
						mh = 7;
						break;
					case CharacterCard.HECATIA_LAPISLAZULI:
						if (life.get() == maxLife.get()) dl += 2;
						else if (life.get() > 1) r += 2;
						else db += 2;
						break;
					case CharacterCard.JUNKO:
						dl += 1;
					case CharacterCard.MORIYA_SUWAKO:
						db += 1;
					case CharacterCard.YASAKA_KANAKO:
						r += 1;
				}
			}
			mh = roleCard.get().getID() == RoleCard.HEROINE ||
					roleCard.get().getID() == RoleCard.EX_BOSS_REVEALED ?
					mh + 1 : mh;

			danmakuLimitProperty().set(dl);
			maxHandSize.set(mh);
			range.set(r);
			distanceBonus.set(db);

			maxLife.set(roleCard.get().getID() == RoleCard.HEROINE ||
					roleCard.get().getID() == RoleCard.EX_BOSS_REVEALED ||
					roleCard.get().getID() == RoleCard.TRUE_PHANTASM_BOSS ||
					roleCard.get().getID() == RoleCard.SECRET_BOSS_DISCOVERED ||
					roleCard.get().getID() == RoleCard.TAG_TEAM
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