package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.anakindeck.Card;
import seedu.address.model.anakindeck.Deck;

/**
 * The API of the AnakinModel component
 */
public interface AnakinModel {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Deck> PREDICATE_SHOW_ALL_DECKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Card> PREDICATE_SHOW_ALL_CARDS = unused -> true;

    void resetData(AnakinReadOnlyAnakin newData);

    AnakinReadOnlyAnakin getAnakin();

    void sort();

    boolean hasDeck(Deck deck);

    void addDeck(Deck deck);

    void deleteDeck(Deck deck);

    void updateDeck(Deck target, Deck editedDeck);

    boolean hasCard(Card card);

    void addCard(Card card);

    void deleteCard(Card card);

    void goIntoDeck(Deck deck);

    void getOutOfDeck();

    void updateCard(Card target, Card editedCard);

    ObservableList<Deck> getFilteredDeckList();

    void updateFilteredDeckList(Predicate<Deck> predicate);

    ObservableList<Card> getFilteredCardList();

    void updateFilteredCardList(Predicate<Card> predicate);

    boolean isInsideDeck();

    boolean canUndoAnakin();

    boolean canRedoAnakin();

    void undoAnakin();

    void redoAnakin();

    void commitAnakin();
}
