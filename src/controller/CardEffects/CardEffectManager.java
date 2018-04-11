package controller.CardEffects;

import controller.game.GameController;
import object.card.Card;
import org.jetbrains.annotations.NotNull;

public abstract class CardEffectManager {

    static GameController gc;

    public abstract void manage(@NotNull Card card, @NotNull GameController gameController);
}
