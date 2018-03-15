package object.card;

import object.exception.InvalidIDException;
import org.jetbrains.annotations.NotNull;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 22-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public final class IncidentCard extends Card {

	public IncidentCard(int id) throws InvalidIDException {
		super(id, Type.INCIDENT);
	}
}
