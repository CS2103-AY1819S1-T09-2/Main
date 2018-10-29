package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.Deck;

/**
 * The API of the Model component
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Deck> PREDICATE_SHOW_ALL_DECKS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Card> PREDICATE_SHOW_ALL_CARDS = unused -> true;

    void resetData(ReadOnlyAnakin newData);

    ReadOnlyAnakin getAnakin();

    void sort();

    boolean hasDeck(Deck deck);

    void addDeck(Deck deck);

    void deleteDeck(Deck deck);

    void updateDeck(Deck target, Deck editedDeck);

    boolean hasCard(Card card);

    void addCard(Card card);

    void deleteCard(Card card);

    void getIntoDeck(Deck deck);

    void getOutOfDeck();

    void updateCard(Card target, Card editedCard);

    ObservableList<Deck> getFilteredDeckList();

    void updateFilteredDeckList(Predicate<Deck> predicate);

    ObservableList<Card> getFilteredCardList();

    void updateFilteredCardList(Predicate<Card> predicate);

    boolean isInsideDeck();

    String exportDeck(Deck deck);

    Deck importDeck (String filepath);

    boolean canUndoAnakin();

    boolean canRedoAnakin();

    void undoAnakin();

    void redoAnakin();

    void commitAnakin();
}
