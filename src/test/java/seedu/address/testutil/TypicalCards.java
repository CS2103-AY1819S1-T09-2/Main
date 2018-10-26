package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;
import seedu.address.model.anakindeck.Name;

/**
 * A utility class containing a list of {@code Card} objects to be used in tests.
 */
public class TypicalCards {

    public static final Card CARD_A = new CardBuilder().withQuestion("An").withAnswer("Empty").build();
    public static final Card CARD_B = new CardBuilder().withQuestion("Street").withAnswer("An").build();
    public static final Card CARD_C = new CardBuilder().withQuestion("Empty").withAnswer("House").build();
    public static final Card CARD_D = new CardBuilder().withQuestion("A").withAnswer("Hole").build();
    public static final Card CARD_E = new CardBuilder().withQuestion("Inside").withAnswer("My").build();
    public static final Card CARD_F = new CardBuilder().withQuestion("Heart").withAnswer("I am").build();
    public static final Card CARD_G = new CardBuilder().withQuestion("All").withAnswer("Alone").build();

    private TypicalCards() {
    } // prevents instantiation

    /**
     * Returns a {@code Deck} with all the typical cards.
     */
    public static Deck getTypicalDeck() {
        Deck ad = new Deck(new Name("HEYYY"), getTypicalCards());
        return ad;
    }

    public static List<Card> getTypicalCards() {
        return new ArrayList<>(Arrays.asList(CARD_A, CARD_B, CARD_C, CARD_D, CARD_E, CARD_F, CARD_G));
    }
}
