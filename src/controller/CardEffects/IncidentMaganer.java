package controller.CardEffects;

import controller.game.GameController;
import object.card.Card;
import object.card.IncidentCard;
import org.jetbrains.annotations.NotNull;

/**
 * DanmakuCG
 * <p>
 * Created by Eric Ramírez Santis on 10/04/2018.
 * Github Account: https://github.com/EricRamirezS
 */
public class IncidentMaganer extends CardEffectManager {

    @Override
    public void manage(@NotNull Card card, @NotNull GameController gameController) {
        gc = gameController;
        switch (card.getID()) {
            case IncidentCard.CRISIS_OF_FAITH:
                manageCrisisOfFaith();
                break;
            case IncidentCard.CROSSING_TO_HIGAN:
                manageCrossingToHigan();
                break;
            case IncidentCard.ENDLESS_PARTY:
                manageEndlessParty();
                break;
            case IncidentCard.ETERNAL_NIGHT:
                manageEternalNight();
                break;
            case IncidentCard.FIVE_IMPOSSIBLE_REQUESTS:
                manageFiveImpossibleRequests();
                break;
            case IncidentCard.GREAT_BARRIER_WEAKENING:
                manageGreatBarrierWeakening();
                break;
            case IncidentCard.GREAT_FAIRY_WARS:
                manageGreatFairyWars();
                break;
            case IncidentCard.LILY_WHITE:
                manageLilyWhite();
                break;
            case IncidentCard.OVERDRIVE:
                manageOverDrive();
                break;
            case IncidentCard.REKINDLE_BLAZING_HELL:
                manageRekindleBlazingHell();
                break;
            case IncidentCard.SAIGYOU_AYAKASHI_BLOOMING:
                manageSaigyouAyakashiBlooming();
                break;
            case IncidentCard.SCARLET_WEATHER_RHAPSODY:
                manageScarletWeatherRhapsody();
                break;
            case IncidentCard.SPRING_SNOW:
                manageSpringSnow();
                break;
            case IncidentCard.UNDEFINED_FANTASTIC_OBJECT:
                manageUndefinedFantasticObject();
                break;
            case IncidentCard.VOYAGE_TO_MAKAI:
                manageVoyageToMakai();
                break;
            case IncidentCard.WORLDLY_DESIRES:
                manageWorldlyDesires();
                break;
            case IncidentCard.DREAM_WORLD:
                manageDreamWorld();
                break;
            case IncidentCard.LUNAR_WARS:
                manageLunarWars();
                break;
            case IncidentCard.SHINING_NEEDLE_CASTLE:
                manageShiningNeedleCastle();
                break;
            case IncidentCard.URBAN_LEGEND_OUTBREAK:
                manageUrbanLegendOutbreak();
        }
    }

    private static void manageCrisisOfFaith() {

    }

    private static void manageCrossingToHigan() {

    }

    private static void manageEndlessParty() {

    }

    private static void manageEternalNight() {

    }

    private static void manageFiveImpossibleRequests() {

    }

    private static void manageGreatBarrierWeakening() {

    }

    private static void manageGreatFairyWars() {

    }

    private static void manageLilyWhite() {

    }

    private static void manageOverDrive() {

    }

    private static void manageRekindleBlazingHell() {

    }

    private static void manageSaigyouAyakashiBlooming() {

    }

    private static void manageScarletWeatherRhapsody() {

    }

    private static void manageSpringSnow() {

    }

    private static void manageUndefinedFantasticObject() {

    }

    private static void manageVoyageToMakai() {

    }

    private static void manageWorldlyDesires() {

    }

    private static void manageDreamWorld() {

    }

    private static void manageLunarWars() {

    }

    private static void manageShiningNeedleCastle() {

    }

    private static void manageUrbanLegendOutbreak() {

    }
}
