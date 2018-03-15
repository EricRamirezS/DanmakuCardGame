package controller.game;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 04-03-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class PhaseController {

	private Step currentStep;
	private GameController gameController;

	PhaseController(GameController gc) {
		gameController = gc;
	}

	enum Step {START_OF_THE_TURN_PHASE, INCIDENT_PHASE, MAIN_PHASE, DISCARD_PHASE, END_OF_THE_TURN_PHASE}
}
