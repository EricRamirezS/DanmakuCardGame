package object.card;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.PopupWindow;

import java.util.Comparator;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 01-03-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class Deck extends Group {

	private ObservableList<Card> deckCards;
	private IntegerProperty deckSize = new SimpleIntegerProperty(0);

	public Deck(ObservableList<Card> cards) {
		Tooltip tooltip = new Tooltip();
		tooltip.setOpacity(0.9);
		tooltip.setAutoFix(true);
		tooltip.setHideOnEscape(true);
		tooltip.setAutoHide(true);
		tooltip.setConsumeAutoHidingEvents(true);
		tooltip.setWrapText(true);
		tooltip.setMinSize(20, 20);
		tooltip.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
		tooltip.textProperty().bind(deckSize.asString());
		Tooltip.install(this, tooltip);
		deckCards = cards;
		prefHeight(10);
		prefWidth(10);

        deckCards.addListener((ListChangeListener<Card>) c -> {
			while (c.next()) {
				if (c.wasAdded()) {
					for (Card card : c.getAddedSubList()) {
						card.setOwner(null);
						card.getMainCardImage().setFaceUp(false);
						getChildren().add(card.getMainCardImage());
					}
					setCardTranslation();
				} else if (c.wasRemoved()) {
					for (Card card : c.getRemoved()) {
						getChildren().remove(card.getMainCardImage());
						card.getMainCardImage().fitHeightProperty().unbind();
					}
					setCardTranslation();
				}
			}
		});
		for (Card card : deckCards) {
			card.getMainCardImage().setFaceUp(false);
			getChildren().add(card.getMainCardImage());
		}
		setCardTranslation();
		parentProperty().addListener(((observable, oldValue, newValue) -> {
			if (newValue != null) {
				for (Card card : deckCards) {
					card.getMainCardImage().fitHeightProperty().bind(((Pane) newValue).heightProperty().multiply(0.95));
				}
			}
		}));
		deckSize.set(deckCards.size());
	}

	private void setCardTranslation() {
		for (Card card : deckCards) {
			card.getMainCardImage().setTranslateZ(-(deckCards.indexOf(card)));
		}
		removeCardOutOfTheDeck();
		orderDeck();
	}

	private void orderDeck() {
		Comparator<Node> comparator = Comparator.comparingDouble(Node::getTranslateZ);
		comparator.reversed();
		FXCollections.sort(getChildren(), comparator);
	}

	private void removeCardOutOfTheDeck() {
		ObservableList<Node> tempList = FXCollections.observableList(getChildren());
		for (Node node : tempList) {
			if (node.getTranslateZ() > 0) {
				getChildren().remove(node);
			}
		}
		deckSize.set(deckCards.size());
	}

	public Card getTopCard(){
		Card card = deckCards.get(deckCards.size()-1);
		deckCards.remove(card);
		return card;
	}
}
