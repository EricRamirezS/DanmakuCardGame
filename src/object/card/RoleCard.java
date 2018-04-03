package object.card;

import object.exception.InvalidIDException;
import org.jetbrains.annotations.NotNull;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 22-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public final class RoleCard extends Card {

	public static final int //region ID
			//BASE
			HEROINE = 1,
			RIVAL = 2,
			STAGE_BOSS_1 = 3,
			STAGE_BOSS_2 = 4,
			STAGE_BOSS_3 = 5,
			ANTI_HEROINE = 6,
			CHALLENGER = 7,
			FINAL_BOSS = 8,
			PARTNER_1 = 9,
			PARTNER_2 = 10,
			EX_MIDBOSS = 11,
			ONE_TRUE_PARTNER = 12,
			EX_BOSS = 13,
			EX_BOSS_REVEALED = 14,
			PHANTASM_BOSS = 15,
			TRUE_PHANTASM_BOSS = 16,
			//LUNATIC EXTRA
			SECRET_BOSS = 17,
			SECRET_BOSS_DISCOVERED = 18,
			LONE_WOLF = 19,
			TAG_TEAM = 20; //endregion

	/**
	 * Creates a RoleCard object base on the given ID
	 *
	 * @param id Card's ID
	 * @throws InvalidIDException If there's no Role Card with that ID.
	 */
	public RoleCard(@NotNull Integer id) throws InvalidIDException {
		super(id, Type.ROLE);
	}
}
