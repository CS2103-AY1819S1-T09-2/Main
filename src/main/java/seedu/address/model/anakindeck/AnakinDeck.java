package seedu.address.model.anakindeck;

import java.util.List;
import java.util.Objects;


/**
 * Represents a Deck inside Anakin.
 */
public class AnakinDeck {

    private final Name name;
    private final AnakinUniqueCardList cards;

    public AnakinDeck(Name name) {
        this.name = name;
        cards = new AnakinUniqueCardList();
    }
    public AnakinDeck(Name name, List<AnakinCard> cards) {
        this(name);
        for (AnakinCard card : cards) {
            this.cards.add(card);
        }
    }

    public Name getName() {
        return name;
    }

    public AnakinUniqueCardList getCards() {
        return cards;
    }

    /**
     * Returns true if 2 decks are the same, or have identical name.
     */
    public boolean isSameDeck(AnakinDeck otherDeck) {
        if (otherDeck == this) {
            return true;
        }

        return otherDeck.getName().equals(getName());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AnakinDeck)) {
            return false;
        }

        return isSameDeck((AnakinDeck) other);
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
