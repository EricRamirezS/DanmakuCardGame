package object.card;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import object.scene.MainScene;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 20-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
@DefaultProperty("image")
public final class CardImage extends ImageView {

	private final Image faceUpImage;
	private boolean faceUp = false;
	private Card card;

    //public static CardImage generateBlankCard(){}

	/**
	 * @param card      the Card that this Card image represents
	 * @param focusable Zoom the card if user is pressing ALT
	 */
    CardImage(Card card, boolean focusable) {
		super();
		this.card = card;
		setPreserveRatio(true);
		rotateProperty().addListener(e -> setFace());
		setFitHeight(10);
		setFitWidth(0);

        if (card == null) {
            faceUpImage = new Image("/image/misc/Blank.png");
        } else {
            faceUpImage = new Image("/image/" + card.getType().toString().toLowerCase() + "/" + card.getID() + ".png");
            setFace();
            if (card.getMainCardImage() != null) {
                setFaceUp(card.getMainCardImage().isFaceUp());
            }
            setRotationAxis(Rotate.Y_AXIS);
            if (focusable) {
                setZoomToNode(this);
            }
            if (card instanceof RoleCard) {
                if (card.getID() == 1 ||
                        card.getID() == 14 ||
                        card.getID() == 16 ||
                        card.getID() == 18 ||
                        card.getID() == 20) {
                    setFaceUp(true);
                } else {
                    setFaceUp(false);
                }
            }
            if (card instanceof CharacterCard) {
                setFaceUp(true);
            }
        }

	}

	/**
	 * Automatically set the Card's face image based
	 * on its rotation value
	 */
	private void setFace() {
		if (getRotate() == 180) {
            setImage(card == null ? new Image("/image/misc/Blank.png") : card.getBackImage());
			setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			faceUp = false;
		} else {
			setImage(faceUpImage);
			setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			faceUp = true;
		}
	}

	/**
	 * Set a flip animation for this Card Image
	 * use returnedValue.start() to start the animation
	 *
	 * @param millis duration of the animation
	 * @return A Timeline with the flipping animation of the current card
	 */
	@NotNull
	public Timeline getFlipAnimation(@NotNull Double millis) {
		KeyValue kvi = new KeyValue(rotateProperty(), getRotate());
		KeyValue kvf = new KeyValue(rotateProperty(), getRotate() - 180);
		KeyFrame kfi = new KeyFrame(Duration.ZERO, kvi);
		KeyFrame kff = new KeyFrame(Duration.millis(millis), kvf);
		return new Timeline(kfi, kff);
	}

	/**
	 * In order to preserve aspect ratio and avoid irregular ImageView Growing
	 * please use this method instead of the default FitHeightProperty()
	 *
	 * @param unbind should unbind properties?
	 * @return The height of the bounding box
	 * @see ImageView
	 */
	public DoubleProperty getFitHeightProperty(boolean unbind) {
		if (unbind) {
			fitHeightProperty().unbind();
			fitWidthProperty().unbind();
		}
		try {
			setFitHeight(10);
			setFitWidth(0);
		} catch (Exception ignored) {
		} //Exception may occur if the property is bound
		return fitHeightProperty();
	}

	/**
	 * In order to preserve aspect ratio and avoid irregular ImageView Growing
	 * please use this method instead of the default FitWidthProperty()
	 *
	 * @param unbind should unbind properties?
	 * @return The width of the bounding box
	 * @see ImageView
	 */
	public DoubleProperty getFitWidthProperty(boolean unbind) {
		if (unbind) {
			fitHeightProperty().unbind();
			fitWidthProperty().unbind();
		}
		try {
			setFitHeight(0);
			setFitWidth(10);
		} catch (Exception ignored) {
		} //Exception may occur if the property is bound
		return fitWidthProperty();
	}

	/**
	 * Set the Card's Image to Face Up
	 *
	 * @deprecated use {@link #setFaceUp(boolean)} instead.
	 */
	@Deprecated
	public void setFaceUp() {
		setRotate(0);
	}

	/**
	 * Set the Card's Image to Face Down.
	 *
	 * @deprecated use {@link #setFaceUp(boolean)} instead.
	 */
	@Deprecated
	public void setFaceDown() {
		setRotate(180);
	}

	/**
	 * Indicates if the card is face up.
	 *
	 * @return true if the card is face up.
	 */
	@Contract(pure = true)
	public boolean isFaceUp() {
		return faceUp;
	}

	/**
	 * Set the Card's face to face up or face Down
	 *
	 * @param isFaceUp is Face Up?
	 */
	public void setFaceUp(boolean isFaceUp) {
		setRotate(isFaceUp ? 0 : 180);
	}

	@Contract(pure = true)
	public Card getCard() {
		return card;
	}


	public void setZoomToNode(@NotNull Node node) {
		node.setOnMouseEntered(e -> {
			ZoomManager.setCardToZoom(this);
			ZoomManager.activeZoom();
		});
		node.setOnMouseExited(e -> ZoomManager.setCardToZoom(null));
		node.sceneProperty().addListener(((observable, oldValue, newValue) -> {
			if (newValue != null) {
				newValue.setOnKeyPressed(ZoomManager::activeZoom);
				newValue.setOnKeyReleased(ZoomManager::cancelZoom);
			}
		}));
	}


}

class ZoomManager {

	private static StackPane zoomPane = new StackPane();
	private static boolean ALT_PRESSED = false;
	private static CardImage cardToZoom = null;

	/**
	 * Zooms the card if the the mouse is over the card when the users
	 * press ALT KEY.
	 *
	 * @param event Key that called this function
	 */
	protected static void activeZoom(@NotNull KeyEvent event) {
		MainScene.displayOptionMenu(event);
		if (event.getCode() == KeyCode.ALT) {
			ALT_PRESSED = true;
			addCardToZoomPane();
		}
	}

	/**
	 * Zooms the card if the the mouse is over the card while the user
	 * is pressing ALT KEY.
	 */
	protected static void activeZoom() {
		if (ALT_PRESSED) {
			addCardToZoomPane();
		}
	}


	/**
	 * clears the zoom pane and removes the zoom pane from the Scene's Root
	 * if the ALT KEY was released.
	 *
	 * @param event Key that called this function
	 */
	protected static void cancelZoom(@NotNull KeyEvent event) {
		if (event.getCode() == KeyCode.ALT) {
			ALT_PRESSED = false;
			if (zoomPane.getScene() != null) {
				((Pane) zoomPane.getScene().getRoot()).getChildren().remove(zoomPane);
				zoomPane.getChildren().clear();
			}
		}
	}

	/**
	 * Set with card is waiting to be zoomed.
	 *
	 * @param card to be zoomed
	 */
	protected synchronized static void setCardToZoom(@Nullable CardImage card) {
		cardToZoom = card;
	}

	/**
	 * Place a card on the zoom pane if there's a card waiting to be zoomed.
	 */
	private static void addCardToZoomPane() {
		if (cardToZoom != null) {
			try {
				((Pane) cardToZoom.getScene().getRoot()).getChildren().add(zoomPane);
				zoomPane.setStyle("-fx-background-color: transparent");
				AnchorPane.setTopAnchor(zoomPane, 0D);
				AnchorPane.setBottomAnchor(zoomPane, 0D);
				AnchorPane.setLeftAnchor(zoomPane, 0D);
				AnchorPane.setRightAnchor(zoomPane, 0D);
				zoomPane.setTranslateZ(-300);
				CardImage zoomedCard = cardToZoom.getCard().getCardImage(false);
				zoomedCard.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, new Color(0, 0, 0, 0.99), 300, 0, 0, 0));
				zoomPane.getChildren().add(zoomedCard);
				zoomedCard.fitHeightProperty().bind(zoomPane.heightProperty());
			} catch (IllegalArgumentException ignored) {
			}
		}
	}
}
