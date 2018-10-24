package seedu.address.testutil;

import java.util.List;

import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;
import seedu.address.model.anakindeck.UniqueCardList;
import seedu.address.model.anakindeck.Name;

/**
 * A utility class to help with building Deck objects.
 */
public class AnakinDeckBuilder {

    public static final String DEFAULT_NAME = "My Deck";

    private Name name;
    private UniqueCardList cards;

    public AnakinDeckBuilder() {
        name = new Name(DEFAULT_NAME);
        cards = new UniqueCardList();
    }

    /**
     * Initializes the DeckBuilder with the data of {@code deckToCopy}.
     */
    public AnakinDeckBuilder(Deck deckToCopy) {
        name = deckToCopy.getName();
        cards = deckToCopy.getCards();
    }

    /**
     * Sets the {@code Name} of the {@code Deck} that we are building.
     */
    public AnakinDeckBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code UniqueCardList} of the {@code Deck} that we are building.
     */
    public AnakinDeckBuilder withCards(List<Card> cardlist) {
        UniqueCardList uniqueCardList = new UniqueCardList();
        uniqueCardList.setCards(cardlist);
        this.cards = uniqueCardList;
        return this;
    }

    public Deck build() {
        return new Deck(name, cards.internalList);
    }

}
