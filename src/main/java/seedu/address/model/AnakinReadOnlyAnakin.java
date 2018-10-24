package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;

/**
 * Unmodifiable view of Anakin
 */
public interface AnakinReadOnlyAnakin {

    /**
     * Returns an unmodifiable view of the decks list.
     * This list will not contain any duplicate decks.
     */
    ObservableList<Deck> getDeckList();

    /**
     * Returns an unmodifiable view of the cards list.
     * This list will not contain any duplicate cards.
     */
    ObservableList<AnakinCard> getCardList();

    boolean isInsideDeck();
     */
     * Returns the state
    /**
}
