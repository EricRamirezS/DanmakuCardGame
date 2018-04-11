package object.card;

import object.exception.InvalidIDException;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 22-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public final class IncidentCard extends Card implements IncidentCardID {


	public IncidentCard(int id) throws InvalidIDException {
		super(id, Type.INCIDENT);
	}
}
