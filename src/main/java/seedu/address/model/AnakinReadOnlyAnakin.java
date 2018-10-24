package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Unmodifiable view of Anakin
 */
public interface AnakinReadOnlyAnakin {

    /**
     * Returns an unmodifiable view of the decks list.
     * This list will not contain any duplicate decks.
     */
    ObservableList<AnakinDeck> getDeckList();

    /**
     * Returns an unmodifiable view of the cards list.
     * This list will not contain any duplicate cards.
     */
    ObservableList<AnakinCard> getCardList();

    /**
     * Returns the state
     */
    boolean isInsideDeck();
}
