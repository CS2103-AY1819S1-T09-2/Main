package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Anakin;
import seedu.address.model.anakindeck.Deck;

/**
 * A utility class containing a list of {@code Deck} objects to be used in tests.
 */
public class TypicalDecks {

    public static final Deck DECK_A = new DeckBuilder().withName("Algebra").build();
    public static final Deck DECK_B = new DeckBuilder().withName("Bacon Salad Recipe").build();
    public static final Deck DECK_C = new DeckBuilder().withName("Calculus").build();
    public static final Deck DECK_D = new DeckBuilder().withName("Darwinian Studies").build();
    public static final Deck DECK_E = new DeckBuilder().withName("Epistemology").build();
    public static final Deck DECK_WITH_CARDS = TypicalCards.getTypicalDeck();

    // Manually added
    public static final Deck DECK_F = new DeckBuilder().withName("Finance").build();
    public static final Deck DECK_G = new DeckBuilder().withName("Geometry").build();

    private TypicalDecks() {
    } // prevents instantiation

    /**
     * Returns an {@code Anakin} with all the typical decks.
     */
    public static Anakin getTypicalAnakin() {
        Anakin ak = new Anakin();
        for (Deck deck : getTypicalDecks()) {
            ak.addDeck(deck);
        }
        return ak;
    }

    /**
     * Returns an {@code Anakin} with all typical decks and inside deck B.
     */

    public static Anakin getTypicalAnakinInDeck() {
        Anakin ak = new Anakin();
        for (Deck deck : getTypicalDecks()) {
            ak.addDeck(deck);
        }

        ak.getIntoDeck(DECK_WITH_CARDS);
        return ak;
    }

    public static List<Deck> getTypicalDecks() {
        return new ArrayList<>(Arrays.asList(DECK_WITH_CARDS, DECK_A, DECK_B, DECK_C, DECK_D, DECK_E));
    }
}
