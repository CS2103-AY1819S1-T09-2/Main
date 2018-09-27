package seedu.address.model;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.person.Deck;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Deck> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in the address book.
     */
    boolean hasPerson(Deck deck);

    /**
     * Deletes the given deck.
     * The deck must exist in the address book.
     */
    void deletePerson(Deck target);

    /**
     * Adds the given deck.
     * {@code deck} must not already exist in the address book.
     */
    void addPerson(Deck deck);

    /**
     * Replaces the given deck {@code target} with {@code editedDeck}.
     * {@code target} must exist in the address book.
     * The deck identity of {@code editedDeck} must not be the same as another existing deck in the address book.
     */
    void updatePerson(Deck target, Deck editedDeck);

    /** Returns an unmodifiable view of the filtered deck list */
    ObservableList<Deck> getFilteredPersonList();

    /**
     * Updates the filter of the filtered deck list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Deck> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();
}
