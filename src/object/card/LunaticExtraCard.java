package object.card;

import object.exception.InvalidIDException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 22-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public final class LunaticExtraCard extends Card {

	public static final int //region ID
			ONE_UP_1 = 1,
			ONE_UP_2 = 2,
			BOMB_1 = 3,
			BOMB_2 = 4,
			BOMB_3 = 5,
			BOMB_4 = 6,
			CAPTURE_SPELLCARD_1 = 7,
			CAPTURE_SPELLCARD_2 = 8,
			FOCUS = 9,
			FULL_POWER = 10,
			GHOST_LANTERN = 11,
			GIFT_OF_YATAGARASE_1 = 12,
			GIFT_OF_YATAGARASE_2 = 13,
			HOURAI_ELIXIR = 14,
			INTERRUPT_1 = 15,
			INTERRUPT_2 = 16,
			JEWELED_PAGODA = 17,
			LASET_SHOT_1 = 18,
			LASET_SHOT_2 = 19,
			MASTER_PLAN = 20,
			OFFERING_1 = 21,
			OFFERING_2 = 22,
			POINT_OF_COLLECTION_1 = 23,
			POINT_OF_COLLECTION_2 = 24,
			PURIFICATION = 25,
			REFLECT_SHOT_1 = 26,
			REFLECT_SHOT_2 = 27,
			REFLECT_SHOT_3 = 28,
			REFLECT_SHOT_4 = 29,
			SCORCHED_EARTH_1 = 30,
			SCORCHED_EARTH_2 = 31,
			SEVEN_STAR_SWORD = 32,
			SPELL_ASSIST_1 = 33,
			SPELL_ASSIST_2 = 34,
			SPIRIT_CAMERA = 35,
			SUPERNATURAL_BORDER = 36,
			TANUKI_LEAF = 37,
			UCHIDES_MALLET = 38,
			VOILE_1 = 39,
			VOILE_2 = 40; //endregion

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

	public LunaticExtraCard(@NotNull Integer id) throws InvalidIDException {
		super(id, Type.LE);
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
