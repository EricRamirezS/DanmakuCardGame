package object.card;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import object.exception.InvalidIDException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 22-02-2018.
 * Github Account: https://github.com/EricRamirezS
 */
public final class CharacterCard extends Card {

	private final boolean spellCardAvailable;
	private final boolean abilityAvailable;
	private final ObjectProperty<CharacterCard> copyingCharacter = new SimpleObjectProperty<>(null);
	private final Source source;

	/**
	 * Creates a main CharacterCard object base on the given ID
	 *
	 * @param id Card's ID
	 * @throws InvalidIDException If there's no CharacterCard with that ID.
	 */
	public CharacterCard(int id) throws InvalidIDException {
		super(id, Type.CHARACTER);
		spellCardAvailable = true;
		abilityAvailable = true;
		source = Source.PRIMARY;
	}

	/**
	 * Use this constructor for recruited Character Only.
	 * <p>
	 * canPlaySpellcard must be set true if the player should be able to
	 * use this Character's Spell Card, else set it as false.
	 * <p>
	 * canUseAbility must be set true if the player should be able to
	 * use this Character's Ability, else set it as false
	 *
	 * @param id               Card's ID
	 * @param canPlaySpellcard Does the player can activate this character's Spellcard?
	 * @param canUseAbility    Does the player can use this character's ability?
	 * @param source           The way this character has been recruited
	 * @throws InvalidIDException If there's no CharacterCard with that ID.
	 *
	 * @see #CharacterCard(int)
	 */
	public CharacterCard(int id, boolean canPlaySpellcard, boolean canUseAbility, @NotNull Source source) throws InvalidIDException {
		super(id, Type.CHARACTER);
		spellCardAvailable = canPlaySpellcard;
		abilityAvailable = canUseAbility;
		this.source = source;
	}

	@Contract(pure = true)
	public boolean isSpellCardAvailable() {
		return spellCardAvailable;
	}

	@Contract(pure = true)
	public boolean isAbilityAvailable() {
		return abilityAvailable;
	}

	public CharacterCard getCopyingCharacter() {
		return copyingCharacter.get();
	}

	public void setCopyingCharacter(CharacterCard copyingCharacter) {
		this.copyingCharacter.set(copyingCharacter);
	}

	@Contract(pure = true)
	public ObjectProperty<CharacterCard> copyingCharacterProperty() {
		return copyingCharacter;
	}

	@Contract(pure = true)
	public Source getSource() {
		return source;
	}

	public enum Source {PRIMARY, ABILITY, ROLE, ITEM}
}
