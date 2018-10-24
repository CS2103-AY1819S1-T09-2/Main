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
public class AnakinTypicalCards {

    public static final Card CARD_A = new AnakinCardBuilder().withQuestion("An").withAnswer("Empty").build();
    public static final Card CARD_B = new AnakinCardBuilder().withQuestion("Street").withAnswer("An").build();
    public static final Card CARD_C = new AnakinCardBuilder().withQuestion("Empty").withAnswer("House").build();
    public static final Card CARD_D = new AnakinCardBuilder().withQuestion("A").withAnswer("Hole").build();
    public static final Card CARD_E = new AnakinCardBuilder().withQuestion("Inside").withAnswer("My").build();
    public static final Card CARD_F = new AnakinCardBuilder().withQuestion("Heart").withAnswer("I am").build();
    public static final Card CARD_G = new AnakinCardBuilder().withQuestion("All").withAnswer("Alone").build();

    private AnakinTypicalCards() {} // prevents instantiation

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
