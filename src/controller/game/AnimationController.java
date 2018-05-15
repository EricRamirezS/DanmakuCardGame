package controller.game;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.util.Duration;
import object.card.Card;
import object.card.CardImage;
import object.card.Deck;
import object.player.Player;
import object.scene.Pos;
import org.jetbrains.annotations.NotNull;

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

        return generateReturnable(tl);
    }

    Timeline getFlipCardAnimation(Deck deck, Deck discardPile) {

        Pos DeckPos = new Pos(deck);
        Pos discardPos = new Pos(discardPile);
        Timeline tl = new Timeline();
        return generateReturnable(tl);
    }

    Timeline getFlipCardAnimation(Card card) {
        CardImage cardImage = card.getMainCardImage();
        KeyValue kv0 = new KeyValue(cardImage.rotateProperty(), cardImage.getRotate());
        KeyValue kv1 = new KeyValue(cardImage.rotateProperty(), cardImage.getRotate() - 180);
        KeyFrame kf0 = new KeyFrame(Duration.ZERO, kv0);
        KeyFrame kf1 = new KeyFrame(Duration.millis(750), kv1);

        Timeline tl = new Timeline(kf0, kf1);
        return generateReturnable(tl);
    }

    Timeline getFlipCardAnimation(CardImage cardImage) {
        KeyValue kv0 = new KeyValue(cardImage.rotateProperty(), cardImage.getRotate());
        KeyValue kv1 = new KeyValue(cardImage.rotateProperty(), cardImage.getRotate() - 180);
        KeyFrame kf0 = new KeyFrame(Duration.ZERO, kv0);
        KeyFrame kf1 = new KeyFrame(Duration.millis(750), kv1);

        Timeline tl = new Timeline(kf0, kf1);
        return generateReturnable(tl);
    }

    Timeline getRevealNewIncidentAnimation() {

        Pos incidentDeckPos = new Pos(gameController.getTableController().getIncidentDeckGroup());
        Card newIncident = gameController.getTableController().getIncidentDeckGroup().getTopCard();
        gameController.getCurrentIncidentsList().add(newIncident);
        CardImage image = newIncident.getMainCardImage();
        Pos newPos = new Pos(image);
        image.setTranslateX(incidentDeckPos.x - newPos.x);
        image.setTranslateY(incidentDeckPos.y - newPos.y);

        Timeline flip = getFlipCardAnimation(newIncident);

        KeyValue kv0x = new KeyValue(image.translateXProperty(), image.getTranslateX());
        KeyValue kv0y = new KeyValue(image.translateYProperty(), image.getTranslateY());
        KeyValue kv1x = new KeyValue(image.translateXProperty(), 0);
        KeyValue kv1y = new KeyValue(image.translateYProperty(), 0);

        KeyFrame kf0 = new KeyFrame(Duration.ZERO, e -> flip.play());
        KeyFrame kf1 = new KeyFrame(flip.getTotalDuration().add(Duration.millis(125)), kv0x, kv0y);
        KeyFrame kf2 = new KeyFrame(flip.getTotalDuration().add(Duration.millis(625)), kv1x, kv1y);

        Timeline tl = new Timeline(kf0, kf1, kf2);

        return generateReturnable(tl);
    }

    void shuffleAnimation(Deck main, Deck Discard) {

    }

    void shuffleAnimation(Deck deck) {

    }

    @NotNull
    private Timeline generateReturnable(@NotNull Timeline tl) {
        return new Timeline(
                new KeyFrame(Duration.ZERO, event -> tl.play()),
                new KeyFrame(tl.getTotalDuration().add(Duration.millis(125)))
        );
    }
}
