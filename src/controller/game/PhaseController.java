package controller.game;

import object.card.IncidentCard;

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

	void start(){
		startOfTheTurnPhaseManager();
	}

	private void startOfTheTurnPhaseManager() {
		Step currentStep = Step.START_OF_THE_TURN_PHASE;
		//TODO Start of the turn effects
		incidentStepManager();
	}

	private void incidentStepManager() {
		Step currentStep = Step.INCIDENT_PHASE;
		if (gameController.getCurrentIncidentsList().size() == 0) {
			IncidentCard incidentCard = (IncidentCard) gameController.getTableController().getIncidentDeckGroup().getTopCard();
		}
	}
}
