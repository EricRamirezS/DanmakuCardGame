package object.player;

import object.card.CharacterCard;
import object.card.RoleCard;
import object.exception.InvalidIDException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 26-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class BotPlayer extends Player {

	public BotPlayer(RoleCard roleCard) throws IOException {
		super(roleCard);
	}

	@Override
	public void playCard() {
		//TODO
	}

	@Override
	public void discardCard() {
		//TODO
	}

	@Override
	public void buyLECard() {
		//TODO
	}

	@Override
	public List<CharacterCard> recruitCharacter(@NotNull CharacterCard[] characters) {
		//TODO
		return null;
	}

	@Override
	@NotNull
	public List<CharacterCard> chooseCharacter(@NotNull List<CharacterCard> characters) {
		try {
			setCharacterCard(new CharacterCard(characters.get(0).getID()));
			characters.remove(0);
		} catch (InvalidIDException ignored) {}
		return characters;
	}

}
