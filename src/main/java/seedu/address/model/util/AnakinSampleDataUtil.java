package seedu.address.model.util;

import seedu.address.model.Anakin;
import seedu.address.model.AnakinReadOnlyAnakin;
import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.model.anakindeck.AnakinQuestion;
import seedu.address.model.anakindeck.Name;

/**
 * Contains utility methods for populating {@code Anakin} with sample decks.
 */
public class AnakinSampleDataUtil {
    private static AnakinDeck[] getSampleDecks() {
        return new AnakinDeck[] {
                new AnakinDeck(new Name("Asking Questions"))
        };
    }

    private static Anakin getSampleCards(Anakin sampleAnakin) {
        for (AnakinDeck sampleDeck : sampleAnakin.getDeckList()) {
            sampleAnakin.getIntoDeck(sampleDeck);
            sampleAnakin.addCard(new AnakinCard(new AnakinQuestion("What is always coming, but never arrives?"),
                    new AnakinAnswer("Tomorrow")));
            sampleAnakin.addCard(new AnakinCard(new AnakinQuestion("What can be broken, but is never held?"),
                    new AnakinAnswer("A promise")));
            sampleAnakin.addCard(new AnakinCard(new AnakinQuestion("What is it that lives if it is fed, and dies if " +
                    "you give it a drink?"), new AnakinAnswer("Fire")));
            sampleAnakin.addCard(new AnakinCard(new AnakinQuestion("What can one catch that is not thrown?"),
                    new AnakinAnswer("A cold")));
            sampleAnakin.addCard(new AnakinCard(new AnakinQuestion(" What is it that if you have, you want to share " +
                    "me, and if you share, you do not have?"), new AnakinAnswer("A secret")));
            sampleAnakin.addCard(new AnakinCard(new AnakinQuestion("If it takes eight men ten hours to build a wall, " +
                    "how long would it take four men?"), new AnakinAnswer("No time, because the wall is already built" +
                    ".")));
        }
        return sampleAnakin;
    }

    private static Anakin initSampleAnakin() {
        Anakin sampleAnakin = new Anakin();
        for (AnakinDeck sampleDeck : getSampleDecks()) {
            sampleAnakin.addDeck(sampleDeck);
        }
        return sampleAnakin;
    }

    public static AnakinReadOnlyAnakin getSampleAnakin() {
        Anakin sampleAnakin = initSampleAnakin();
        sampleAnakin = getSampleCards(sampleAnakin);
        return sampleAnakin;
    }
}
