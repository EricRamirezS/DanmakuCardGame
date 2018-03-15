package object.card;

import controller.game.GameController;
import javafx.scene.image.Image;
import object.exception.InvalidIDException;
import object.player.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 20-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class Card {

	public static final double CHARACTER_HEIGHT = 1200;
	public static final double CHARACTER_WIDTH = 1715;
	public static final double CARD_HEIGHT = 1200;
	public static final double CARD_WIDTH = 850;
	private static final Image deckBackImage = new Image("/image/deck/0.png");
	private static final Image leBackImage = new Image("/image/le/0.png");
	private static final Image roleBackImage = new Image("/image/role/0.png");
	private static final Image characterBackImage = new Image("/image/character/0.png");
	private static final Image incidentBackImage = new Image("/image/incident/0.png");
	final ResourceBundle prop;
	private final int ID;
	private final Season season;
	private final Type type;
	private CardImage cardImage;

	private Player owner = null;

	/**
	 * Create a Card Object and load it base properties
	 * This method may cause an InvalidIDException if there's no card
	 * registered under the given ID
	 *
	 * @param id   Card's ID
	 * @param type Card's Type
	 * @throws InvalidIDException If there's no card of the given type
	 *                            with that ID.
	 */
	Card(int id, @NotNull Type type) throws InvalidIDException {
		ID = id;
		this.type = type;
		try {
			prop = ResourceBundle.getBundle("property/card/" + type.toString().toLowerCase() + "/card", new Locale(ID + ""));
			season = convertSeason(prop.getString("Season"));
		} catch (Exception e) {
			throw new InvalidIDException("Please check the " + type.toString().toLowerCase() + " card ID",
					new Throwable("Could not " + type.toString().toLowerCase() + " card a letter with that ID"));
		}
		cardImage = getCardImage(true);
	}

	/**
	 * Converts the String property text of Season in a
	 * {@link Season} enumerator.
	 *
	 * @param season text from the property file.
	 * @return A {@link Season} enumerator based on the String text provided.
	 * @see Season
	 */
	@Contract(pure = true)
	private Season convertSeason(@NotNull String season) {
		switch (season) {
			case "Spring":
				return Season.SPRING;
			case "Summer":
				return Season.SUMMER;
			case "Autumn":
				return Season.AUTUMN;
			default:
				return Season.WINTER;
		}
	}

	/**
	 * Returns a CardImage extends ImageView object
	 * with the card graphics, that lasts over time, retaining any modification.
	 * <p>
	 * Use this to place and manipulate a CardImage pane
	 * By default this CardImage is always zoomable.
	 * @return A final CardImage.
	 */
	public CardImage getMainCardImage() {
		return cardImage;
	}

	/**
	 * Returns a new CardImage extends ImageView object
	 * with the card graphics.
	 * <p>
	 * By default this CardImage is always zoomable.
	 * @return A new CardImage.
	 */
	public CardImage getCardImage() {
		return getCardImage(true);
	}

	/**
	 * Returns a new CardImage extends ImageView object with the card graphics.
	 * The zoomable argument should be set as true.
	 * This means that if the user is pressing ALT
	 * they'll be able to see the card at full screen size.
	 * <p>
	 * This method should only be use by the MainScene class or when need
	 * to duplicate the Card's CardImage.
	 *
	 * @param zoomable may the card be zoomable?
	 * @return a new Card Image with the card's graphics
	 * @see CardImage
	 * @see object.scene.MainScene
	 */
	public CardImage getCardImage(boolean zoomable) {
		boolean isOwner;
		try {
			isOwner = GameController.players.get(0) == getOwner();
		} catch (IndexOutOfBoundsException e) {
			isOwner = false;
		}
		CardImage iv = new CardImage(this, zoomable);
		if (!zoomable) {
			iv.setFaceUp(isOwner || iv.isFaceUp());
		}
		return iv;
	}

	/**
	 * gets the default height of the card based on its type
	 * @return default height of the card's Image.
	 */
	public double getDefaultHeight() {
		switch (type) {
			case CHARACTER:
				return CHARACTER_HEIGHT;
			default:
				return CARD_HEIGHT;
		}
	}

	/**
	 * gets the default width of the card based on its type
	 * @return default width of the card's Image.
	 */
	public double getDefaultWidth() {
		switch (type) {
			case CHARACTER:
				return CHARACTER_WIDTH;
			default:
				return CARD_WIDTH;
		}
	}

	/**
	 * get the ID number of the card
	 * @return ID number
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Get the tye of the card
	 * @return the type of the card.
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Get the back cover of the card based on its type
	 * @return Image with the card's back cover
	 */
	Image getBackImage() {
		switch (type) {
			case CHARACTER:
				return characterBackImage;
			case ROLE:
				return roleBackImage;
			case LE:
				return leBackImage;
			case INCIDENT:
				return incidentBackImage;
			default:
				return deckBackImage;
		}
	}

	/**
	 * Returns the owner Player of the card.
	 * @return Player who owns this card, null if there's no owner for this card
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * Sets the owner of this card
	 * @param owner the Player who owns this card
	 */
	public void setOwner(@Nullable Player owner) {
		this.owner = owner;
	}

	public enum Season {AUTUMN, WINTER, SPRING, SUMMER}

	public enum Type {CHARACTER, DECK, LE, INCIDENT, ROLE}
}
