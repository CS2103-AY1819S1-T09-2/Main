package seedu.address.testutil;

import seedu.address.model.Anakin;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * A utility class to help with building Anakin objects.
 * Example usage: <br>
 *     {@code Anakin ak = new AnakinBuilder().withDeck(DECK_A).build();}
 */

public class AnakinBuilder {
    private Anakin anakin;

    public AnakinBuilder() {
        anakin = new Anakin();
    }

    public AnakinBuilder(Anakin anakin) {
        this.anakin = anakin;
    }

    /**
     * Adds a new {@code Deck} to the {@code Anakin} that we are building.
     */
    public AnakinBuilder withDeck(AnakinDeck deck) {
        anakin.addDeck(deck);
        return this;
    }

    public Anakin build() {
        return anakin;
    }
}
