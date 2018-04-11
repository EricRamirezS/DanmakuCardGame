package controller.CardEffects;

import controller.game.GameController;
import object.card.Card;
import object.card.DeckCard;
import org.jetbrains.annotations.NotNull;

public class DeckCardManager extends CardEffectManager {

    @Override
    public void manage(@NotNull Card card, @NotNull GameController gameController) {
        int ID = card.getID();
        if (DeckCard.SHOOT_CARD.contains(ID)) {
        } else if (DeckCard.ONE_UP_CARD.contains(ID)) {
        } else if (DeckCard.BOMB_CARD.contains(ID)) {
        } else if (DeckCard.BORROW_CARD.contains(ID)) {
        } else if (DeckCard.CAPTURE_SPELLCARD_CARD.contains(ID)) {
        } else if (DeckCard.FOCUS_CARD.contains(ID)) {
        } else if (DeckCard.GRAZE_CARD.contains(ID)) {
        } else if (DeckCard.GRIMOIRE_CARD.contains(ID)) {
        } else if (DeckCard.KOURINDOU_CARD.contains(ID)) {
        } else if (DeckCard.LASER_SHOT_CARD.contains(ID)) {
        } else if (DeckCard.LAST_WORLD_CARD.contains(ID)) {
        } else if (DeckCard.MASTER_PLAN_CARD.contains(ID)) {
        } else if (DeckCard.MELEE_CARD.contains(ID)) {
        } else if (DeckCard.MINI_HAKKERO_CARD.contains(ID)) {
        } else if (DeckCard.PARTY_CARD.contains(ID)) {
        } else if (DeckCard.POWER_CARD.contains(ID)) {
        } else if (DeckCard.SEAL_AWAY_CARD.contains(ID)) {
        } else if (DeckCard.SORCERERS_SUTRA_SCROLL_CARD.contains(ID)) {
        } else if (DeckCard.SPIRITUAL_ATTACK_CARD.contains(ID)) {
        } else if (DeckCard.STOPWATCH_CARD.contains(ID)) {
        } else if (DeckCard.SUPERNATURAL_BORDER_CARD.contains(ID)) {
        } else if (DeckCard.TEMPEST_CARD.contains(ID)) {
        } else if (DeckCard.VOILE_CARD.contains(ID)) {
        } else if (DeckCard.ERASE_SHOT_CARD.contains(ID)) {
        } else if (DeckCard.NIMBLE_CLOTH_CARD.contains(ID)) {
        } else if (DeckCard.OCCULT_BALL_CARD.contains(ID)) {
        } else if (DeckCard.PURIFICATION_CARD.contains(ID)) {
        } else if (DeckCard.REVENGE_SHOT_CARD.contains(ID)) {
        } else if (DeckCard.SPELL_ASSIST_CARD.contains(ID)) {
        }

    }
}
