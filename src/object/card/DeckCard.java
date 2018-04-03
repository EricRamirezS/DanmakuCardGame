package object.card;

import object.exception.InvalidIDException;
import org.jetbrains.annotations.Contract;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 22-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public final class DeckCard extends Card {
	public final static int //region ID
			//BASE
			ONE_UP_1 = 1,
			ONE_UP_2 = 2,
			BOMB_1 = 3,
			BOMB_2 = 4,
			BOMB_3 = 5,
			BOMB_4 = 6,
			BORROW_1 = 7,
			BORROW_2 = 8,
			CAPTURE_SPELLCARD = 9,
			FOCUS_1 = 10,
			FOCUS_2 = 11,
			GRAZE_1 = 12,
			GRAZE_2 = 13,
			GRAZE_3 = 14,
			GRAZE_4 = 15,
			GRAZE_5 = 16,
			GRAZE_6 = 17,
			GRAZE_7 = 18,
			GRAZE_8 = 19,
			GRAZE_9 = 20,
			GRAZE_10 = 21,
			GRAZE_11 = 22,
			GRAZE_12 = 23,
			GRAZE_13 = 24,
			GRAZE_14 = 25,
			GRIMOIRE_1 = 26,
			GRIMOIRE_2 = 27,
			KOURINDOU = 28,
			LASER_SHOT = 29,
			LAST_WORLD = 30,
			MASTER_PLAN = 31,
			MELEE = 32,
			MINI_HACKKERO = 33,
			PARTY = 34,
			POWER_1 = 35,
			POWER_2 = 36,
			POWER_3 = 37,
			POWER_4 = 38,
			POWER_5 = 39,
			POWER_6 = 40,
			SEAL_AWAY_1 = 41,
			SEAL_AWAY_2 = 42,
			SEAL_AWAY_3 = 43,
			SEAL_AWAY_4 = 44,
			SHOOT_1 = 45,
			SHOOT_2 = 46,
			SHOOT_3 = 47,
			SHOOT_4 = 48,
			SHOOT_5 = 49,
			SHOOT_6 = 50,
			SHOOT_7 = 51,
			SHOOT_8 = 52,
			SHOOT_9 = 53,
			SHOOT_10 = 54,
			SHOOT_11 = 55,
			SHOOT_12 = 56,
			SHOOT_13 = 57,
			SHOOT_14 = 58,
			SHOOT_15 = 59,
			SHOOT_16 = 60,
			SHOOT_17 = 61,
			SHOOT_18 = 62,
			SHOOT_19 = 63,
			SHOOT_20 = 64,
			SHOOT_21 = 65,
			SHOOT_22 = 66,
			SHOOT_23 = 67,
			SHOOT_24 = 68,
			SORCERERS_SUTRA_SCROLL = 69,
			SPIRITUAL_ATTACK_1 = 70,
			SPIRITUAL_ATTACK_2 = 71,
			SPIRITUAL_ATTACK_3 = 72,
			SPIRITUAL_ATTACK_4 = 73,
			SPIRITUAL_ATTACK_5 = 74,
			SPIRITUAL_ATTACK_6 = 75,
			STOPWATCH = 76,
			SUPERNATURAL_BORDER_1 = 77,
			SUPERNATURAL_BORDER_2 = 78,
			TEMPEST = 79,
			VOILE = 80,
			//LUNATIC EXTRA
			ERASE_SHOT_1 = 81,
			ERASE_SHOT_2 = 82,
			ERASE_SHOT_3 = 83,
			ERASE_SHOT_4 = 84,
			ERASE_SHOT_5 = 85,
			ERASE_SHOT_6 = 86,
			ERASE_SHOT_7 = 87,
			ERASE_SHOT_8 = 88,
			ERASE_SHOT_9 = 89,
			NIMBLE_CLOTH_1 = 90,
			NIMBLE_CLOTH_2 = 91,
			OCCULT_BALL_1 = 92,
			OCCULT_BALL_2 = 93,
			PURIFICATION = 94,
			REVENGE_SHOT_1 = 95,
			REVENGE_SHOT_2 = 96,
			REVENGE_SHOT_3 = 97,
			REVENGE_SHOT_4 = 98,
			SPELL_ASSIST_1 = 99,
			SPELL_ASSIST_2 = 100; //endregion

	private final int pointValue;
	private final boolean action;
	private final boolean reaction;
	private final boolean healing;
	private final boolean invocation;
	private final boolean item;
	private final boolean defense;
	private final boolean dodge;
	private final boolean danmaku;
	private final boolean artifact;
	private final boolean power;
	private final boolean instant;

	public DeckCard(int id) throws InvalidIDException {
		super(id, Type.DECK);
		instant = prop.getString("Instant").equalsIgnoreCase("true");
		power = prop.getString("Power").equalsIgnoreCase("true");
		artifact = prop.getString("Power").equalsIgnoreCase("true");
		danmaku = prop.getString("Power").equalsIgnoreCase("true");
		dodge = prop.getString("Power").equalsIgnoreCase("true");
		defense = prop.getString("Power").equalsIgnoreCase("true");
		item = prop.getString("Power").equalsIgnoreCase("true");
		invocation = prop.getString("Power").equalsIgnoreCase("true");
		healing = prop.getString("Power").equalsIgnoreCase("true");
		reaction = prop.getString("Power").equalsIgnoreCase("true");
		action = prop.getString("Power").equalsIgnoreCase("true");
		pointValue = Integer.parseInt(prop.getString("Point_Value"));
	}

	@Contract(pure = true)
	public int getPointValue() {
		return pointValue;
	}

	@Contract(pure = true)
	public boolean isAction() {
		return action;
	}

	@Contract(pure = true)
	public boolean isReaction() {
		return reaction;
	}

	@Contract(pure = true)
	public boolean isHealing() {
		return healing;
	}

	@Contract(pure = true)
	public boolean isInvocation() {
		return invocation;
	}

	@Contract(pure = true)
	public boolean isItem() {
		return item;
	}

	@Contract(pure = true)
	public boolean isDefense() {
		return defense;
	}

	@Contract(pure = true)
	public boolean isDodge() {
		return dodge;
	}

	@Contract(pure = true)
	public boolean isDanmaku() {
		return danmaku;
	}

	@Contract(pure = true)
	public boolean isArtifact() {
		return artifact;
	}

	@Contract(pure = true)
	public boolean isPower() {
		return power;
	}

	@Contract(pure = true)
	public boolean isInstant() {
		return instant;
	}
}
