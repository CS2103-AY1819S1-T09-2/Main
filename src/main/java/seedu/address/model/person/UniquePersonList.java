package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A deck is considered unique by comparing using {@code Deck#isSameDeck(Deck)}. As such, adding and updating of
 * persons uses Deck#isSameDeck(Deck) for equality so as to ensure that the deck being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a deck uses Deck#equals(Object) so
 * as to ensure that the deck with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Deck#isSameDeck(Deck)
 */
public class UniquePersonList implements Iterable<Deck> {

    private final ObservableList<Deck> internalList = FXCollections.observableArrayList();

    /**
     * Returns true if the list contains an equivalent deck as the given argument.
     */
    public boolean contains(Deck toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDeck);
    }

    /**
     * Adds a deck to the list.
     * The deck must not already exist in the list.
     */
    public void add(Deck toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the deck {@code target} in the list with {@code editedDeck}.
     * {@code target} must exist in the list.
     * The deck identity of {@code editedDeck} must not be the same as another existing deck in the list.
     */
    public void setPerson(Deck target, Deck editedDeck) {
        requireAllNonNull(target, editedDeck);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameDeck(editedDeck) && contains(editedDeck)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDeck);
    }

    /**
     * Removes the equivalent deck from the list.
     * The deck must exist in the list.
     */
    public void remove(Deck toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code decks}.
     * {@code decks} must not contain duplicate decks.
     */
    public void setPersons(List<Deck> decks) {
        requireAllNonNull(decks);
        if (!personsAreUnique(decks)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(decks);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Deck> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }

    @Override
    public Iterator<Deck> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniquePersonList // instanceof handles nulls
                        && internalList.equals(((UniquePersonList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code decks} contains only unique decks.
     */
    private boolean personsAreUnique(List<Deck> decks) {
        for (int i = 0; i < decks.size() - 1; i++) {
            for (int j = i + 1; j < decks.size(); j++) {
                if (decks.get(i).isSameDeck(decks.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
