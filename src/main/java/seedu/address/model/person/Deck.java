package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Deck in Anakin.
 */

public class Deck {
    private List<Card> cards;
    private String name;

    public Deck(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public Deck(String name, List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void editName(String newName) {
        name = newName;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name);
        return builder.toString();
    }
}
