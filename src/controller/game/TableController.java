package controller.game;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import object.card.Deck;
import object.player.Board;
import object.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TableController {

    private final GameController gameController;
    @FXML
    private GridPane mainGrid;
    @FXML
    private GridPane centerGrid;
    @FXML
    private ColumnConstraints colLE;
    @FXML
    private ColumnConstraints colCurrentIncident;
    @FXML
    private Pane deckPane;
    @FXML
    private Pane leDeckPane;
    @FXML
    private Pane incidentDeckPane;
    @FXML
    private Pane deckDiscardPane;
    @FXML
    private Pane leDiscardDeckPane;
    @FXML
    private Pane pastIncidentPane;
    @FXML
    private GridPane activeIncidentGridPane;

    private Deck mainDeckGroup;
    private Deck mainDiscardPileGroup;
    private Deck leDeckGroup;
    private Deck leDiscardPileGroup;
    private Deck incidentDeckGroup;
    private Deck pastIncidentPileGroup;
    private Deck currentIncidentDeck;

    TableController(GameController gameController) {
        this.gameController = gameController;
    }

    @FXML
    void initialize() {
        mainDeckGroup = new Deck(gameController.getMainDeckList());
        mainDiscardPileGroup = new Deck(gameController.getMainDiscardPileList());
        leDeckGroup = new Deck(gameController.getLeDeckList());
        leDiscardPileGroup = new Deck(gameController.getLeDiscardPileList());
        incidentDeckGroup = new Deck(gameController.getIncidentDeckList());
        pastIncidentPileGroup = new Deck(gameController.getPastIncidentPileList());
        setDeck(mainDeckGroup, deckPane);
        setDeck(mainDiscardPileGroup, deckDiscardPane);
        setDeck(leDeckGroup, leDeckPane);
        setDeck(leDiscardPileGroup, leDiscardDeckPane);
        setDeck(incidentDeckGroup, incidentDeckPane);
        setDeck(pastIncidentPileGroup, pastIncidentPane);


        if (!gameController.isLunaticExtra()) {
            centerGrid.getColumnConstraints().remove(colLE);
            centerGrid.getChildren().removeAll(leDeckPane, leDiscardDeckPane);

        } else {
            colCurrentIncident.minWidthProperty().bind(centerGrid.widthProperty().divide(4));
        }
        //centerGrid.getColumnConstraints().get(centerGrid.getColumnConstraints().size() - 1).minWidthProperty().bind(centerGrid.widthProperty().divide(3));
        //colCurrentIncident.minWidthProperty().bind(centerGrid.widthProperty().divide(centerGrid.getColumnConstraints().size()));
    }

    void placePlayerBoards() {
        //removing any player board from mainGrid
        //By default there should be none tho -w-U
        for (Node node : mainGrid.getChildren()) {
            if (node instanceof Board) {
                mainGrid.getChildren().remove(node);
            }
        }
        int nOfPlayers = GameController.players.size(); //Getting the number of player
        List<Pane> boardsRaw = new ArrayList<>();
        List<Group> boards = new ArrayList<>();
        for (Player player : GameController.players) {
            // Placing Players' board in a List
            boardsRaw.add(player.getBoardPane());
            // Placing Players' board in a Group in order to rotating them
            // Groups adjust it size to fit their children even if they were rotated
            boards.add(new Group(player.getBoardPane()));
        }
        int i = 0;
        //adding all Groups with Board in the Main Grid Pane
        mainGrid.getChildren().addAll(boards);
        //Placing and rotating Boards in their respective place based on the number of players
        switch (nOfPlayers) {
            case 4: //region 4 Players

                //Player 1
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 3);
                GridPane.setColumnSpan(boards.get(i), 4);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(4 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(0);

                //Player 2
                GridPane.setColumnIndex(boards.get(i), 0);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 4);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().subtract(2));
                boardsRaw.get(i++).setRotate(90);

                //Player 3
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 4);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(4 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 4
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 4);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().subtract(2));
                boardsRaw.get(i).setRotate(270);
                break;//endregion
            case 5://region 5 Players

                //Player 1
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 3);
                GridPane.setColumnSpan(boards.get(i), 4);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(4 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(0);

                //Player 2
                GridPane.setColumnIndex(boards.get(i), 0);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().subtract(2));
                boardsRaw.get(i++).setRotate(90);

                //Player 3
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 4
                GridPane.setColumnIndex(boards.get(i), 3);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 5
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 4);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().subtract(2));
                boardsRaw.get(i).setRotate(270);

                break;//endregion
            case 6://region 6 Players

                //Player 1
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 3);
                GridPane.setColumnSpan(boards.get(i), 4);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(4 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(0);

                //Player 2
                GridPane.setColumnIndex(boards.get(i), 0);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 4);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().subtract(2));
                boardsRaw.get(i++).setRotate(90);

                //Player 3
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 4
                GridPane.setColumnIndex(boards.get(i), 3);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 5
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i++).setRotate(270);

                //Player 6
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 2);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i).setRotate(270);

                break;//endregion
            case 7://region 7 Players

                //Player 1
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 3);
                GridPane.setColumnSpan(boards.get(i), 4);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(4 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(0);

                //Player 2
                GridPane.setColumnIndex(boards.get(i), 0);
                GridPane.setRowIndex(boards.get(i), 2);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i++).setRotate(90);

                //Player 3
                GridPane.setColumnIndex(boards.get(i), 0);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i++).setRotate(90);

                //Player 4
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 5
                GridPane.setColumnIndex(boards.get(i), 3);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 6
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i++).setRotate(270);

                //Player 7
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 2);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i).setRotate(270);

                break;//endregion
            case 8://region 7 Players

                //Player 1
                GridPane.setColumnIndex(boards.get(i), 3);
                GridPane.setRowIndex(boards.get(i), 3);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(0);

                //Player 2
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 3);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(0);

                //Player 3
                GridPane.setColumnIndex(boards.get(i), 0);
                GridPane.setRowIndex(boards.get(i), 2);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i++).setRotate(90);

                //Player 4
                GridPane.setColumnIndex(boards.get(i), 0);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i++).setRotate(90);

                //Player 5
                GridPane.setColumnIndex(boards.get(i), 1);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 6
                GridPane.setColumnIndex(boards.get(i), 3);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 2);
                GridPane.setRowSpan(boards.get(i), 1);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.heightProperty().multiply(0.28).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.widthProperty().multiply(2 * 0.1666666666).subtract(2));
                boardsRaw.get(i++).setRotate(180);

                //Player 7
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 0);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i++).setRotate(270);


                //Player 8
                GridPane.setColumnIndex(boards.get(i), 5);
                GridPane.setRowIndex(boards.get(i), 2);
                GridPane.setColumnSpan(boards.get(i), 1);
                GridPane.setRowSpan(boards.get(i), 2);
                boardsRaw.get(i).prefHeightProperty().bind(mainGrid.widthProperty().multiply(0.166666666).subtract(2));
                boardsRaw.get(i).prefWidthProperty().bind(mainGrid.heightProperty().divide(2).subtract(2));
                boardsRaw.get(i).setRotate(270);

                break;//endregion
        }
    }

    public void setDeck(Deck deck, Pane pane) {
        pane.getChildren().add(deck);
    }

    public Deck getMainDeckGroup() {
        return mainDeckGroup;
    }

    public Deck getMainDiscardPileGroup() {
        return mainDiscardPileGroup;
    }

    public Deck getLeDeckGroup() {
        return leDeckGroup;
    }

    public Deck getLeDiscardPileGroup() {
        return leDiscardPileGroup;
    }

    public Deck getIncidentDeckGroup() {
        return incidentDeckGroup;
    }

    public Deck getPastIncidentPileGroup() {
        return pastIncidentPileGroup;
    }

    private void orderCurrentIncidents() {

    }
}
