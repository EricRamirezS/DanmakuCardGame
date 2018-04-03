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
	private int currentTurn = 0;

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

	/**
	 * Get the Player who is playing the current turn.
	 * @return The player who's playing this turn.
	 */
	Player getCurrentPlayer(){
		return playersByTurn.get(currentTurn);
	}

	/**
	 * Get the Player who is played the previous turn.
	 * @return The player who's played the previous turn.
	 */
	Player getLastPlater(){
		if (currentTurn-1>=0){
			return playersByTurn.get(currentTurn-1);
		} else {
			return playersByTurn.get(playersByTurn.size()-1);
		}
	}

	/**
	 * Get the Player who will play the next turn.
	 * @return The player who will play the next turn.
	 */
	Player getNextPlayer(){
		if (currentTurn+1<=playersByTurn.size()){
			return playersByTurn.get(currentTurn+1);
		} else {
			return playersByTurn.get(0);
		}
	}

	/**
	 * Moves the turn to the next Player
	 */
	void nextTurn(){
		if (currentTurn++>=playersByTurn.size()){
			currentTurn=0;
		}
	}
}
