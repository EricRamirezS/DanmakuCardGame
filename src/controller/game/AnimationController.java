package controller.game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;
import object.card.Card;
import object.card.Deck;
import object.player.Player;
import object.scene.Pos;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 27-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
class AnimationController {

    private final GameController gameController;

    AnimationController(GameController gameController) {
        this.gameController = gameController;
    }

    Timeline getDrawCardAnimation(Deck deck, final Player player) {
        final Card cardObject = deck.getTopCard();

        Group card = new Group(cardObject.getMainCardImage());
        Pos deckPos = new Pos(deck);
        Pos playerFrontPos = player.getCentralPos();
        Pos playerBackPos = player.getExitPos();
        double finalRotation = player.getBoardPane().getRotate();

        card.setTranslateX(deckPos.x);
        card.setTranslateY(deckPos.y);

        gameController.getParent().getChildren().add(card);

        KeyValue keyPosX0 = new KeyValue(card.translateXProperty(), deckPos.x);
        KeyValue keyPosY0 = new KeyValue(card.translateYProperty(), deckPos.y);
        KeyValue keyRot0 = new KeyValue(card.rotateProperty(), deck.getRotate());
        KeyValue keyPosX1 = new KeyValue(card.translateXProperty(), playerFrontPos.x);
        KeyValue keyPosY1 = new KeyValue(card.translateYProperty(), playerFrontPos.y);
        KeyValue keyRot1 = new KeyValue(card.rotateProperty(), finalRotation);
        KeyValue keyPosX2 = new KeyValue(card.translateXProperty(), playerBackPos.x);
        KeyValue keyPosY2 = new KeyValue(card.translateYProperty(), playerBackPos.y);

        KeyFrame kf0 = new KeyFrame(Duration.ZERO, keyPosX0, keyPosY0, keyRot0);
        KeyFrame kf1 = new KeyFrame(Duration.millis(125), keyPosX1, keyPosY1, keyRot1);
        KeyFrame kf2 = new KeyFrame(Duration.millis(250), keyPosX2, keyPosY2);

        Timeline tl = new Timeline(kf0, kf1, kf2);

        tl.setOnFinished(event -> {
            gameController.getParent().getChildren().remove(card);
            card.getChildren().clear();
            card.setTranslateX(0);
            card.setTranslateY(0);
            player.drawCard(cardObject);
        });

        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> tl.play()),
                new KeyFrame(Duration.millis(375))
        );
    }

    Timeline getRevealIncidentAnimation(Deck deck) {

        Pos DeckPos = new Pos(deck);
        Timeline tl = new Timeline();
        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> tl.play()),
                new KeyFrame(Duration.millis(0))
        );
    }
}
