package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Anakin;
import seedu.address.model.anakindeck.Deck;

/**
 * A utility class containing a list of {@code Deck} objects to be used in tests.
 */
public class AnakinTypicalDecks {

<<<<<<< Updated upstream
    public static final AnakinDeck DECK_A = new AnakinDeckBuilder().withName("Algebra").build();
    public static final AnakinDeck DECK_B = new AnakinDeckBuilder().withName("Bacon Salad Recipe").build();
    public static final AnakinDeck DECK_C = new AnakinDeckBuilder().withName("Calculus").build();
    public static final AnakinDeck DECK_D = new AnakinDeckBuilder().withName("Darwinian Studies").build();
    public static final AnakinDeck DECK_E = new AnakinDeckBuilder().withName("Epistemology").build();
    public static final AnakinDeck DECK_WITH_CARDS =  AnakinTypicalCards.getTypicalDeck();
=======
    public static final Deck DECK_A = new AnakinDeckBuilder().withName("Algebra").build();
    public static final Deck DECK_B = new AnakinDeckBuilder().withName("Bacon Salad Recipe").build();
    public static final Deck DECK_C = new AnakinDeckBuilder().withName("Calculus").build();
    public static final Deck DECK_D = new AnakinDeckBuilder().withName("Darwinian Studies").build();
    public static final Deck DECK_E = new AnakinDeckBuilder().withName("Epistemology").build();
>>>>>>> Stashed changes

    // Manually added
    public static final Deck DECK_F = new AnakinDeckBuilder().withName("Finance").build();
    public static final Deck DECK_G = new AnakinDeckBuilder().withName("Geometry").build();

    private AnakinTypicalDecks() {} // prevents instantiation

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

<<<<<<< Updated upstream
    /**
     * Returns an {@code Anakin} with all typical decks and inside deck B.
     */

    public static Anakin getTypicalAnakinInDeck() {
        Anakin ak = new Anakin();
        for (AnakinDeck deck : getTypicalDecks()) {
            ak.addDeck(deck);
        }

        ak.getIntoDeck(DECK_WITH_CARDS);
        return ak;
    }

    public static List<AnakinDeck> getTypicalDecks() {
        return new ArrayList<>(Arrays.asList(DECK_WITH_CARDS, DECK_A, DECK_B, DECK_C, DECK_D, DECK_E, DECK_F, DECK_G));
=======
    public static List<Deck> getTypicalDecks() {
        return new ArrayList<>(Arrays.asList(DECK_A, DECK_B, DECK_C, DECK_D, DECK_E, DECK_F, DECK_G));
>>>>>>> Stashed changes
    }
}
