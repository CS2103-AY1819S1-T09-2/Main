package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * The API of the AnakinModel component
 */
public interface AnakinModel {

    /** {@code Predicate} that always evaluate to true */
    Predicate<AnakinDeck> PREDICATE_SHOW_ALL_DECKS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<AnakinCard> PREDICATE_SHOW_ALL_CARDS = unused -> true;

    void resetData(AnakinReadOnlyAnakin newData);

    AnakinReadOnlyAnakin getAnakin();

    boolean hasDeck(AnakinDeck deck);

    void addDeck(AnakinDeck deck);

    void deleteDeck(AnakinDeck deck);

    void updateDeck(AnakinDeck target, AnakinDeck editedDeck);

    boolean hasCard(AnakinCard card);

    void addCard(AnakinCard card);

    void deleteCard(AnakinCard card);

    void goIntoDeck(AnakinDeck deck);

    void getOutOfDeck();

    void updateCard(AnakinCard target, AnakinCard editedCard);

    ObservableList<AnakinDeck> getFilteredDeckList();

    void updateFilteredDeckList(Predicate<AnakinDeck> predicate);

    ObservableList<AnakinCard> getFilteredCardList();

    void updateFilteredCardList(Predicate<AnakinCard> predicate);

    boolean isInsideDeck();

    boolean canUndoAnakin();

    boolean canRedoAnakin();

    void undoAnakin();

    void redoAnakin();

    void commitAnakin();
}
