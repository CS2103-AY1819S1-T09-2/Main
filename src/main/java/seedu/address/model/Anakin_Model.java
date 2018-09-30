package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.Anakin_deck.Anakin_Card;
import seedu.address.model.Anakin_deck.Anakin_Deck;

/**
 * The API of the Anakin_Model component
 */
public interface Anakin_Model {

    void resetData(Anakin_ReadOnlyAnakin newData);

    Anakin_ReadOnlyAnakin getAnakin();

    boolean hasDeck(Anakin_Deck deck);

    void addDeck(Anakin_Deck deck);

    void deleteDeck(Anakin_Deck deck);

    void updateDeck(Anakin_Deck target, Anakin_Deck editedDeck);

    boolean hasCard(Anakin_Deck deck, Anakin_Card card);

    void addCard(Anakin_Deck deck, Anakin_Card card);

    void deleteCard(Anakin_Deck deck, Anakin_Card card);

    void updateCard(Anakin_Deck deck, Anakin_Card target, Anakin_Card editedCard);

    ObservableList<Anakin_Deck> getFilteredDeckList();

    void updateFilteredDeckList(Predicate<Anakin_Deck> predicate);

    boolean canUndoAnakin();

    boolean canRedoAnakin();

    void undoAnakin();

    void redoAnakin();

    void commitAnakin();
}
