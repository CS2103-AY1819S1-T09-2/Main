package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.model.anakindeck.Name;

/**
 * A utility class containing a list of {@code Card} objects to be used in tests.
 */
public class AnakinTypicalCards {

    public static final AnakinCard CARD_A = new AnakinCardBuilder().withQuestion("An").withAnswer("Empty").build();
    public static final AnakinCard CARD_B = new AnakinCardBuilder().withQuestion("Street").withAnswer("An").build();
    public static final AnakinCard CARD_C = new AnakinCardBuilder().withQuestion("Empty").withAnswer("House").build();
    public static final AnakinCard CARD_D = new AnakinCardBuilder().withQuestion("A").withAnswer("Hole").build();
    public static final AnakinCard CARD_E = new AnakinCardBuilder().withQuestion("Inside").withAnswer("My").build();
    public static final AnakinCard CARD_F = new AnakinCardBuilder().withQuestion("Heart").withAnswer("I am").build();
    public static final AnakinCard CARD_G = new AnakinCardBuilder().withQuestion("All").withAnswer("Alone").build();

    private AnakinTypicalCards() {} // prevents instantiation

    /**
     * Returns a {@code Deck} with all the typical cards.
     */
    public static AnakinDeck getTypicalDeck() {
        AnakinDeck ad = new AnakinDeck(new Name("HEYYY"), getTypicalCards());
        return ad;
    }

    public static List<AnakinCard> getTypicalCards() {
        return new ArrayList<>(Arrays.asList(CARD_A, CARD_B, CARD_C, CARD_D, CARD_E, CARD_F, CARD_G));
    }
}
