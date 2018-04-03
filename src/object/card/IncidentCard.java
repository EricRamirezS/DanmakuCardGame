package object.card;

import object.exception.InvalidIDException;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 22-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public final class IncidentCard extends Card {

	public static final int //region ID
			//BASE
			CRISIS_OF_FAITH = 1,
			CROSSING_TO_HIGAN = 2,
			ENDLESS_PARTY = 3,
			ETERNAL_NIGHT = 4,
			FIVE_IMPOSSIBLE_REQUESTS = 5,
			GREAT_BARRIER_WEAKENING = 6,
			GREAT_FAIRY_WARS = 7,
			LILY_WHITE = 8,
			OVERDRIVE = 9,
			REKINDLE_BLAZING_HELL = 10,
			SAIGYOU_AYAKASHI_BLOOMING = 11,
			SCARLET_WEATHER_RHAPSODY = 12,
			SPRING_SNOW = 13,
			UNDEFINED_FANTASTIC_OBJECT = 14,
			VOYAGE_TO_MAKAI = 15,

			//LUNATIC EXTRA
			WORDLY_DESIRED = 16,
			DREAM_WORLD = 17,
			LUNAR_WARS = 18,
			SHINING_NEEDLE_CASTLE = 19,
			URBAN_LEGEND_OUTBREAK = 20; //endregion

	public IncidentCard(int id) throws InvalidIDException {
		super(id, Type.INCIDENT);
	}
}
