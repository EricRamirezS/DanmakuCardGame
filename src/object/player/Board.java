package object.player;

import javafx.beans.binding.Bindings;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import object.card.Card;
import object.card.CardImage;
import object.card.CharacterCard;
import object.scene.Pos;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Board extends GridPane {

    private final Player player;
    @FXML
    private Pane posPane;
    @FXML
    private Pane posExitPane;
    @FXML
    private HBox topHBox;
    @FXML
    private HBox bottomHBox;
    @FXML
    private GridPane gridHand;
    @FXML
    private GridPane gridExtraCharacter;
    @FXML
    private GridPane gridItem;
    @FXML
    private ImageView paneCharacter;
    @FXML
    private ImageView paneRole;
    @FXML
    private Label lblLife;
    @FXML
    private Label lblDanmaku;
    @FXML
    private Label lblRange;
    @FXML
    private Label lblDistance;
    @FXML
    private Label lblSpellcard;
    @FXML
    private ImageView extraAbility1;
    @FXML
    private ImageView extraAbility2;
    @FXML
    private ImageView extraRole;
    @FXML
    private ImageView extraItem;
    @FXML
    HBox handHBox;

    Board(Player player) {
        this.player = player;
    }

    @FXML
    void initialize() {
        player.getHand().addListener((ListChangeListener<Card>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Card card : c.getAddedSubList()) {
                        card.setOwner(player);
                    }
                }
            }
            updateHand();
        });
        player.getItems().addListener((ListChangeListener<Card>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Card card : c.getAddedSubList()) {
                        card.setOwner(player);
                    }
                }
            }
            updateItems();
        });
        player.getExtraCharacterCards().addListener((ListChangeListener<Card>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    for (Card card : c.getAddedSubList()) {
                        card.setOwner(player);
                    }
                }
            }
            updateExtraCharacters();
        });
        player.roleCardProperty().addListener((observableValue, oldValue, newValue) -> {
            newValue.setOwner(player);
            updateRole();
        });
        player.characterCardProperty().addListener((observableValue, oldValue, newValue) -> updateMainCharacter());
        String fontDanmaku = getClass().getResource("/css/MPlantin_Danmaku.ttf").toExternalForm();
        Font font = new Font("Garamond", heightProperty().doubleValue() / (20));
        Font fontD = Font.loadFont(fontDanmaku, heightProperty().doubleValue() / (20));
        lblLife.setFont(font);
        lblDanmaku.setFont(fontD);
        lblRange.setFont(font);
        lblDistance.setFont(font);
        lblSpellcard.setFont(fontD);
        heightProperty().addListener((observable, oldValue, newValue) -> {
            lblLife.setFont(new Font(lblLife.getFont().getFamily(), newValue.doubleValue() / (11.5)));
            lblDanmaku.setFont(new Font(lblDanmaku.getFont().getFamily(), newValue.doubleValue() / (11.5)));
            lblRange.setFont(new Font(lblRange.getFont().getFamily(), newValue.doubleValue() / (11.5)));
            lblDistance.setFont(new Font(lblDistance.getFont().getFamily(), newValue.doubleValue() / (11.5)));
            lblSpellcard.setFont(new Font(lblSpellcard.getFont().getFamily(), newValue.doubleValue() / (11.5)));
        });
        lblLife.textProperty().bind(Bindings.concat(
                "\u2665", player.lifeProperty().asString(), "/", player.maxLifeProperty().asString(), "\u2665"));
        lblDanmaku.textProperty().bind(Bindings.concat(
                "$", player.danmakuPlayedProperty().get() ? "\u274C" : "\u2713", ":", player.danmakuCountProperty().asString(), "/", player.danmakuLimitProperty().asString()));
        lblSpellcard.textProperty().bind(Bindings.concat(
                "%", player.spellcardPlayedProperty().get() && !player.spellcardAvailableProperty().get() ? "\u274C" : "\u2713"));
        lblRange.textProperty().bind(Bindings.concat(
                "Range[", player.rangeProperty().asString(), "]"));
        lblDistance.textProperty().bind(Bindings.concat(
                "Dist[+", player.distanceBonusProperty().asString(), "]"));

    }

    private void updateExtraCharacters() {
        List<CharacterCard> list = player.getExtraCharacterCards();
        boolean ability2 = false;
        gridExtraCharacter.getChildren().clear();
        for (CharacterCard character : list) {
            switch (character.getSource()) {
                case ABILITY:
                    if (ability2) {
                        extraAbility2 = character.getCardImage();
                        gridExtraCharacter.getChildren().add(extraAbility2);
                        GridPane.setRowIndex(extraAbility2, 1);
                        extraAbility2.fitHeightProperty().bind(heightProperty().divide(100D / 44 / 4));
                    } else {
                        extraAbility1 = character.getCardImage();
                        gridExtraCharacter.getChildren().add(extraAbility1);
                        GridPane.setRowIndex(extraAbility2, 0);
                        extraAbility2.fitHeightProperty().bind(heightProperty().divide(100D / 44 / 4));
                    }
                    break;
                case ROLE:
                    extraRole = character.getCardImage();
                    gridExtraCharacter.getChildren().add(extraRole);
                    GridPane.setRowIndex(extraRole, 2);
                    extraRole.fitHeightProperty().bind(heightProperty().divide(100D / 44 / 4));
                    break;
                case ITEM:
                    extraItem = character.getCardImage();
                    gridExtraCharacter.getChildren().add(extraItem);
                    GridPane.setRowIndex(extraItem, 3);
                    extraItem.fitHeightProperty().bind(heightProperty().divide(100D / 44 / 4));
            }
        }
    }

    private void updateHand() {
        List<Card> cards = player.getHand();
        placeCardOnGrid(gridHand, cards);
        if (player instanceof HumanPlayer) {
            for (Card card : cards) {
                CardImage cardImage = card.getMainCardImage();
                cardImage.setFaceUp(true);
            }
        } else {
            for (Card card : cards) {
                CardImage cardImage = card.getMainCardImage();
                cardImage.setFaceUp(false);
            }
        }
    }

    private void updateItems() {
        List<Card> cards = player.getItems();
        placeCardOnGrid(gridItem, cards);
        for (Node node : gridItem.getChildren()) {
            CardImage cardImage = (CardImage) node;
            cardImage.setFaceUp(true);
        }
    }

    private void updateRole() {
        if (player.getRoleCard() != null) {
            topHBox.getChildren().clear();
            paneRole = player.getRoleCard().getCardImage();
            topHBox.getChildren().add(paneRole);
            topHBox.getChildren().addAll(handHBox);
            paneRole.fitHeightProperty().bind(heightProperty().divide(100D / 44));
        }
    }

    private void updateMainCharacter() {
        if (player.getCharacterCard() != null) {
            bottomHBox.getChildren().clear();
            paneCharacter = player.getCharacterCard().getCardImage();
            bottomHBox.getChildren().add(paneCharacter);
            bottomHBox.getChildren().addAll(gridExtraCharacter, gridItem);
            paneCharacter.fitHeightProperty().bind(heightProperty().divide(100D / 44));
        }

    }

    private void placeCardOnGrid(@NotNull GridPane grid, @NotNull List<Card> cards) {
        grid.getChildren().clear();
        grid.getColumnConstraints().clear();
        int nOfCards = cards.size();
        int i = 0;
        for (Card card : cards) {
            ColumnConstraints col = new ColumnConstraints();


            CardImage cardPane = card.getMainCardImage();
            cardPane.fitHeightProperty().bind(heightProperty().divide(100D / 44));
            Pane pane = new Pane();

            pane.getChildren().add(cardPane);
            grid.getChildren().add(pane);
            col.setFillWidth(false);
            //col.setMinWidth(1);
            //col.setPercentWidth(100D / (nOfCards /*+ 2)  * (i == nOfCards ? 3 : 1)*/));
            if (i == 0) {
                col.setHalignment(nOfCards == 1 ? HPos.CENTER : HPos.RIGHT);
                col.setHgrow(Priority.SOMETIMES);
            } else if (i == nOfCards - 1) {
                col.setHalignment(HPos.LEFT);
                col.setHgrow(Priority.SOMETIMES);
            } else {
                col.setHalignment(HPos.CENTER);
                col.setHgrow(Priority.NEVER);
            }
            grid.getColumnConstraints().add(col);
            GridPane.setColumnIndex(pane, i++);
        }
    }

    Pos getCentralPos() {
        return new Pos(posPane);
    }

    Pos getExitPos() {
        return new Pos(posExitPane);
    }
}
