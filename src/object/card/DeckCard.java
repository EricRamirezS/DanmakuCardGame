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
