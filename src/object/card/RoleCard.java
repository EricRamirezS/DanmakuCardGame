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
