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

	public static final int //region ID
			//BASE
			ALICE_MARGATROID = 1,
			CIRNO = 2,
			HAKUREI_REIMU = 3,
			HIJIRI_BYAKUREN = 4,
			HINANAWI_TENSHI = 5,
			HONG_MEILING = 6,
			IBUKI_SUIKA = 7,
			IZAYOI_SAKUYA = 8,
			KAMISHIRASAWA_KEINE = 9,
			KAWASHIRO_NITORI = 10,
			KAZAMI_YUUKA = 11,
			KIRISAME_MARISA = 12,
			KOCHIYA_SANAE = 13,
			KOMEIJI_SATORI = 14,
			KONPAKU_YOUMU = 15,
			MONONOBE_NO_FUTO = 16,
			PATCHOULI_KNOWLEDGE = 17,
			REISEN_UDONGEIN_INABA = 18,
			REIUJI_UTSUHO = 19,
			REMILIA_SCARLET = 20,
			SHAMEIMARU_AYA = 21,
			TOYOSATOMIMI_NO_MIKO = 22,
			YAKOGORO_EIRIN = 23,
			YAKUMO_YUKARI = 24,

			//LUNATIC EXTRA
			FLANDRE_SCARLET = 25,
			FUJIWARA_NO_MOKOU = 26,
			FUTATSUIWA_MAMIZOU = 27,
			HECATIA_LAPISLAZULI = 28,
			HORIKAWA_RAIKO = 29,
			HOSHIGUMA_YUUGI = 30,
			HOUJUU_NUE = 31,
			HOUWAISAN_KAGUYA = 32,
			JUNKO = 33,
			KAENBYOU_RIN = 34,
			KIJIN_SEIJA = 35,
			HATA_NO_KOKORO = 36,
			KOMEIJI_KOISHI = 37,
			KUMOI_ICHIRIN = 38,
			NAGAE_IKU = 39,
			ONOZUKA_KOMACHI = 40,
			SAIGYOUJI_YUYUKO = 41,
			SHIKIEIKI_YAMAXANADU = 42,
			MORIYA_SUWAKO = 43,
			TATARA_KOGASA = 44,
			TORAMARU_SHOU = 45,
			USAMI_SUMIREKO = 46,
			YAKUMO_RAN = 47,
			YASAKA_KANAKO = 48; //endregion

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
