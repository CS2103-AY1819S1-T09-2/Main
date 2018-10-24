package seedu.address.model.util;

import seedu.address.model.Anakin;
import seedu.address.model.AnakinReadOnlyAnakin;
import seedu.address.model.anakindeck.Answer;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;
import seedu.address.model.anakindeck.Question;
import seedu.address.model.anakindeck.Name;

/**
 * Contains utility methods for populating {@code Anakin} with sample decks.
 */
public class AnakinSampleDataUtil {
    /**
     * @param sampleAnakin
     * @return sampleAnakin with a sample deck
     */
    private static Anakin addSampleDeck(Anakin sampleAnakin) {
        Deck sampleDeck = new Deck(new Name("Asking Questions"));
        sampleAnakin.addDeck(sampleDeck);
        sampleAnakin.getIntoDeck(sampleDeck);
        sampleAnakin.addCard(new Card(new Question("What is always coming, but never arrives?"),
                new Answer("Tomorrow")));
        sampleAnakin.addCard(new Card(new Question("What can be broken, but is never held?"),
                new Answer("A promise")));
        sampleAnakin.addCard(new Card(new Question("What is it that lives if it is fed, and dies if you " +
                "give it a drink?"), new Answer("Fire")));
        sampleAnakin.addCard(new Card(new Question("What can one catch that is not thrown?"),
                new Answer("A cold")));
        sampleAnakin.addCard(new Card(new Question("What is it that if you have, you want to share me, " +
                "and if you share, you do not have?"), new Answer("A secret")));
        sampleAnakin.addCard(new Card(new Question("If it takes eight men ten hours to build a wall, "
                + "how long would it take four men?"), new Answer("No time, because the wall is already built"
                + ".")));
        return sampleAnakin;
    }

    public static AnakinReadOnlyAnakin getSampleAnakin() {
        Anakin sampleAnakin = new Anakin();
        addSampleDeck(sampleAnakin);
        return sampleAnakin;
    }
}
