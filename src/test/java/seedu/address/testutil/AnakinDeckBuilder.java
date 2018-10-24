package seedu.address.testutil;

import java.util.List;

import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.model.anakindeck.AnakinUniqueCardList;
import seedu.address.model.anakindeck.Name;

/**
 * A utility class to help with building AnakinDeck objects.
 */
public class AnakinDeckBuilder {

    public static final String DEFAULT_NAME = "My Deck";

    private Name name;
    private AnakinUniqueCardList cards;

    public AnakinDeckBuilder() {
        name = new Name(DEFAULT_NAME);
        cards = new AnakinUniqueCardList();
    }

    /**
     * Initializes the DeckBuilder with the data of {@code deckToCopy}.
     */
    public AnakinDeckBuilder(AnakinDeck deckToCopy) {
        name = deckToCopy.getName();
        cards = deckToCopy.getCards();
    }

    /**
     * Sets the {@code Name} of the {@code AnakinDeck} that we are building.
     */
    public AnakinDeckBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code AnakinUniqueCardList} of the {@code AnakinDeck} that we are building.
     */
    public AnakinDeckBuilder withCards(List<AnakinCard> cardlist) {
        AnakinUniqueCardList anakinUniqueCardList = new AnakinUniqueCardList();
        anakinUniqueCardList.setCards(cardlist);
        this.cards = anakinUniqueCardList;
        return this;
    }

    public AnakinDeck build() {
        return new AnakinDeck(name, cards.internalList);
    }

}
