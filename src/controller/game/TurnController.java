package controller.game;

import object.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 04-03-2018.
 * Github Account: https://github.com/EricRamirezS
 */
class TurnController {

	private GameController gameController;

	/**
	 * The first element of this list is always the
	 */
	private List<Player> playersByTurn = new ArrayList<>();

	TurnController(GameController gc) {
		gameController = gc;
		setFirstTurn();
	}

	private void setFirstTurn() {
		boolean heroineAdded = false;
		for (Player player : GameController.getPlayers()) {
			if (!heroineAdded) {
				if (player.getRoleCard().getID() == 1) {
					playersByTurn.add(player);
					heroineAdded = true;
				}
			} else {
				playersByTurn.add(player);
			}
		}
		for (Player player : GameController.getPlayers()) {
			if (GameController.getPlayers().size() == playersByTurn.size()) {
				break;
			} else {
				playersByTurn.add(player);
			}
		}
	}

	List<Player> getPlayersByTurn() {
		return playersByTurn;
	}
}
