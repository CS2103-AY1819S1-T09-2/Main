package seedu.address.model;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Anakin_ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.Anakin_AnakinChangedEvent;
import seedu.address.model.Anakin_deck.Anakin_Card;
import seedu.address.model.Anakin_deck.Anakin_Deck;

/**
 * Represents the in-memory model of Anakin data.
 */
public class Anakin_ModelManager extends Anakin_ComponentManager implements Anakin_Model {
    public static final Logger logger = LogsCenter.getLogger(Anakin_ModelManager.class);

    private final Anakin_VersionedAnakin versionedAnakin;
    private final FilteredList<Anakin_Deck> filteredDecks;
    // The filteredCards is not assigned. Should have methods to assign filteredCards (when user is inside a deck).
    private FilteredList<Anakin_Card> filteredCards;

    /**
     * Initializes a Anakin_ModelManager with the given Anakin and userPrefs.
     */
    public Anakin_ModelManager(Anakin_ReadOnlyAnakin anakin, UserPrefs userPrefs) {
        super();
        requireAllNonNull(anakin, userPrefs);

        logger.fine("Initializing with anakin: " + anakin + " and user prefs " + userPrefs);

        versionedAnakin = new Anakin_VersionedAnakin(anakin);
        filteredDecks = new FilteredList<>(versionedAnakin.getDeckList());
    }

    public Anakin_ModelManager() {
        this(new Anakin(), new UserPrefs());
    }

    @Override
    public void resetData(Anakin_ReadOnlyAnakin newData) {
        versionedAnakin.resetData(newData);
        indicateAnakinChanged();
    }

    @Override
    public Anakin_ReadOnlyAnakin getAnakin() {
        return null;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAnakinChanged() {
        raise(new Anakin_AnakinChangedEvent(versionedAnakin));
    }

    @Override
    public boolean hasDeck(Anakin_Deck deck) {
        requireAllNonNull(deck);
        return versionedAnakin.hasDeck(deck);
    }

    @Override
    public void deleteDeck(Anakin_Deck deck) {
        versionedAnakin.removeDeck(deck);
        indicateAnakinChanged();
    }

    @Override
    public void addDeck(Anakin_Deck deck) {
        versionedAnakin.addDeck(deck);
        updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
        indicateAnakinChanged();
    }

    @Override
    public void updateDeck(Anakin_Deck target, Anakin_Deck editedDeck) {
        requireAllNonNull(target, editedDeck);

        versionedAnakin.updateDeck(target, editedDeck);
        indicateAnakinChanged();
    }

    @Override
    public boolean hasCard(Anakin_Card card) {
        // TODO
        return true;
    }

    @Override
    public void deleteCard(Anakin_Card card) {
        // TODO
    }

    @Override
    public void addCard(Anakin_Card card) {
        // TODO
    }

    @Override
    public void updateCard(Anakin_Card target, Anakin_Card editedCard) {
        // TODO
    }

    //=========== Filtered Deck List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Deck} backed by the internal list of
     * {@code versionedAnakin}
     */
    @Override
    public ObservableList<Anakin_Deck> getFilteredDeckList() {
        return FXCollections.unmodifiableObservableList(filteredDecks);
    }

    @Override
    public void updateFilteredDeckList(Predicate<Anakin_Deck> predicate) {
        requireAllNonNull(predicate);
        filteredDecks.setPredicate(predicate);
    }

    //=========== Filtered Card List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Card} backed by the internal list of
     * {@code currentDeck}
     */
    @Override
    public ObservableList<Anakin_Card> getFilteredCardList() {
        // TODO: throws exception when user is not inside any decks
        return FXCollections.unmodifiableObservableList(filteredCards);
    }

    @Override
    public void updateFilteredCardList(Predicate<Anakin_Card> predicate) {
        // TODO: throws exception when user is not inside any decks
        requireAllNonNull(predicate);
        filteredCards.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAnakin() {
        return versionedAnakin.canUndo();
    }

    @Override
    public boolean canRedoAnakin() {
        return versionedAnakin.canRedo();
    }

    @Override
    public void undoAnakin() {
        versionedAnakin.undo();
        indicateAnakinChanged();
    }

    @Override
    public void redoAnakin() {
        versionedAnakin.canRedo();
        indicateAnakinChanged();
    }

    @Override
    public void commitAnakin() {
        versionedAnakin.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof Anakin_ModelManager)) {
            return false;
        }

        // state check
        Anakin_ModelManager other = (Anakin_ModelManager) obj;
        return versionedAnakin.equals(other.versionedAnakin)
                && filteredDecks.equals(other.filteredDecks);
    }
}
