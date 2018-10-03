package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.AnakinDeck.AnakinCard;
import seedu.address.model.AnakinDeck.AnakinDeck;

/**
 * The API of the AnakinModel component
 */
public interface AnakinModel {

    /** {@code Predicate} that always evaluate to true */
    Predicate<AnakinDeck> PREDICATE_SHOW_ALL_DECKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<AnakinCard> PREDICATE_SHOW_ALL_CARDS = unused -> true;

    void resetData(Anakin_ReadOnlyAnakin newData);

    Anakin_ReadOnlyAnakin getAnakin();

    boolean hasDeck(AnakinDeck deck);

    void addDeck(AnakinDeck deck);

    void deleteDeck(AnakinDeck deck);

    void updateDeck(AnakinDeck target, AnakinDeck editedDeck);

    boolean hasCard(AnakinCard card);

    void addCard(AnakinCard card);

    void deleteCard(AnakinCard card);

    void updateCard(AnakinCard target, AnakinCard editedCard);

    ObservableList<AnakinDeck> getFilteredDeckList();

    void updateFilteredDeckList(Predicate<AnakinDeck> predicate);

    ObservableList<AnakinCard> getFilteredCardList();

    void updateFilteredCardList(Predicate<AnakinCard> predicate);

    boolean canUndoAnakin();

    boolean canRedoAnakin();

    void undoAnakin();

    void redoAnakin();

    void commitAnakin();
}
