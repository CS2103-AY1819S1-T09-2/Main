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
import seedu.address.model.AnakinDeck.AnakinCard;
import seedu.address.model.AnakinDeck.AnakinDeck;

/**
 * Represents the in-memory model of Anakin data.
 */
public class AnakinModelManager extends Anakin_ComponentManager implements AnakinModel {
    public static final Logger logger = LogsCenter.getLogger(AnakinModelManager.class);

    private final Anakin_VersionedAnakin versionedAnakin;
    private final FilteredList<AnakinDeck> filteredDecks;
    // The filteredCards is not assigned. Should have methods to assign filteredCards (when user is inside a deck).
    private FilteredList<AnakinCard> filteredCards;

    /**
     * Initializes a AnakinModelManager with the given Anakin and userPrefs.
     */
    public AnakinModelManager(Anakin_ReadOnlyAnakin anakin, UserPrefs userPrefs) {
        super();
        requireAllNonNull(anakin, userPrefs);

        logger.fine("Initializing with anakin: " + anakin + " and user prefs " + userPrefs);

        versionedAnakin = new Anakin_VersionedAnakin(anakin);
        filteredDecks = new FilteredList<>(versionedAnakin.getDeckList());
    }

    public AnakinModelManager() {
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
    public boolean hasDeck(AnakinDeck deck) {
        requireAllNonNull(deck);
        return versionedAnakin.hasDeck(deck);
    }

    @Override
    public void deleteDeck(AnakinDeck deck) {
        versionedAnakin.removeDeck(deck);
        indicateAnakinChanged();
    }

    @Override
    public void addDeck(AnakinDeck deck) {
        versionedAnakin.addDeck(deck);
        updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
        indicateAnakinChanged();
    }

    @Override
    public void updateDeck(AnakinDeck target, AnakinDeck editedDeck) {
        requireAllNonNull(target, editedDeck);

        versionedAnakin.updateDeck(target, editedDeck);
        indicateAnakinChanged();
    }

    @Override
    public boolean hasCard(AnakinCard card) {
        // TODO
        return true;
    }

    @Override
    public void deleteCard(AnakinCard card) {
        // TODO
    }

    @Override
    public void addCard(AnakinCard card) {
        // TODO
    }

    @Override
    public void updateCard(AnakinCard target, AnakinCard editedCard) {
        // TODO
    }

    //=========== Filtered Deck List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Deck} backed by the internal list of
     * {@code versionedAnakin}
     */
    @Override
    public ObservableList<AnakinDeck> getFilteredDeckList() {
        return FXCollections.unmodifiableObservableList(filteredDecks);
    }

    @Override
    public void updateFilteredDeckList(Predicate<AnakinDeck> predicate) {
        requireAllNonNull(predicate);
        filteredDecks.setPredicate(predicate);
    }

    //=========== Filtered Card List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Card} backed by the internal list of
     * {@code currentDeck}
     */
    @Override
    public ObservableList<AnakinCard> getFilteredCardList() {
        // TODO: throws exception when user is not inside any decks
        return FXCollections.unmodifiableObservableList(filteredCards);
    }

    @Override
    public void updateFilteredCardList(Predicate<AnakinCard> predicate) {
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
        if (!(obj instanceof AnakinModelManager)) {
            return false;
        }

        // state check
        AnakinModelManager other = (AnakinModelManager) obj;
        return versionedAnakin.equals(other.versionedAnakin)
                && filteredDecks.equals(other.filteredDecks);
    }
}
