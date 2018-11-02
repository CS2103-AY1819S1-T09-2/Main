package seedu.address.model.deck;

import java.util.List;
import java.util.Objects;


/**
 * Represents a Deck inside Anakin.
 */
public class Deck {

    public static final Deck NULLDECK = new Deck(new Name("NULLDECK"));

    private final Name name;
    private final UniqueCardList cards;

    public Deck(Name name) {
        this.name = name;
        cards = new UniqueCardList();
    }

    public Deck(Name name, List<Card> cards) {
        this(name);
        for (Card card : cards) {
            this.cards.add(card);
        }
    }

    public Name getName() {
        return name;
    }

    public UniqueCardList getCards() {
        return cards;
    }

    /**
     * Returns true if 2 decks are the same, or have identical name.
     */
    public boolean isSameDeck(Deck otherDeck) {
        if (otherDeck == this) {
            return true;
        }

        if (otherDeck == null) {
            return false;
        }

        return otherDeck.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Deck)) {
            return false;
        }

        return isSameDeck((Deck) other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        return builder.toString();
    }

}
