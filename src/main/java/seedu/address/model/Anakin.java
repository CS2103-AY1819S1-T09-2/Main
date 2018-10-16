package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.anakindeck.AnakinAnswer;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.model.anakindeck.AnakinExceptions.DeckNotFoundException;
import seedu.address.model.anakindeck.AnakinQuestion;
import seedu.address.model.anakindeck.AnakinUniqueCardList;
import seedu.address.model.anakindeck.AnakinUniqueDeckList;

/**
 * Wraps all data at the Anakin level
 * Duplicates are not allowed (by .isSameDeck comparison)
 */
public class Anakin implements AnakinReadOnlyAnakin {

    private final AnakinUniqueDeckList decks;

    private boolean isInsideDeck;
    // Represent the current list of cards (when user get into a deck)
    private AnakinUniqueCardList cards;

    // Represents the list of cards displayed on the UI
    private AnakinUniqueCardList displayedCards;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        decks = new AnakinUniqueDeckList();
        cards = new AnakinUniqueCardList();
        displayedCards = new AnakinUniqueCardList();
    }

    public Anakin() { }

    /**
     * Creates an Anakin using the Decks in the {@code toBeCopied}
     */
    public Anakin(AnakinReadOnlyAnakin toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the deck list with {@code decks}.
     * {@code decks} must not contain duplicate decks.
     */
    public void setDecks(List<AnakinDeck> decks) {
        this.decks.setDecks(decks);
    }

    /**
     * Replaces the contents of the card list with {@code cards}.
     * {@code cards} must not contain duplicate cards.
     */
    public void setCards(List<AnakinCard> cards) {
        this.cards.setCards(cards);
    }

    /**
     * Resets the existing data of this {@code Anakin} with {@code newData}.
     */
    public void resetData(AnakinReadOnlyAnakin newData) {
        requireNonNull(newData);

        setDecks(newData.getDeckList());
        setCards(newData.getCardList());
    }

    //// navigating operations

    /**
     * Navigating into a deck
     */
    public void getIntoDeck(AnakinDeck deck) {
        requireNonNull(deck);
        isInsideDeck = true;
        cards = deck.getCards();
        displayedCards.setCards(deck.getCards());
    }

    /**
     * Navigating out of the current deck
     */
    public void getOutOfDeck() {
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        isInsideDeck = false;
        cards = new AnakinUniqueCardList();
        displayedCards.clear();
    }

    /**
     * Return true if user is inside a deck
     */
    public boolean isInsideDeck() {
        return isInsideDeck;
    }

    //// deck-level operations

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in Anakin.
     */
    public boolean hasDeck(AnakinDeck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    /**
     * Adds a deck to the Anakin.
     * The deck must not already exist in the Anakin.
     */
    public void addDeck(AnakinDeck d) {
        decks.add(d);
    }

    /**
     * Replaces the given deck {@code target} in the list with {@code editedDeck}.
     * {@code target} must exist in the Anakin.
     * The deck identity of {@code editedDeck} must not be the same as another existing deck in the Anakin.
     */
    public void updateDeck(AnakinDeck target, AnakinDeck editedDeck) {
        requireNonNull(editedDeck);

        decks.setDeck(target, editedDeck);
    }

    /**
     * Removes {@code key} from this {@code Anakin}.
     * {@code key} must exist in the Anakin.
     */
    public void removeDeck(AnakinDeck key) {
        decks.remove(key);
    }

    //// card-level operations

    /**
     * Returns true if a card with the same identity as {@code card} exists in current deck.
     */
    public boolean hasCard(AnakinCard card) {
        requireNonNull(card);
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        return cards.contains(card);
    }

    /**
     * Adds a card to the current deck.
     * The card must not already exist in the current deck.
     */
    public void addCard(AnakinCard c) {
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        cards.add(c);
        displayedCards.setCards(cards);
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the current deck.
     * The card identity of {@code editedCard} must not be the same as another existing card in the current deck.
     */
    public void updateCard(AnakinCard target, AnakinCard editedCard) {
        requireNonNull(editedCard);
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        cards.setCard(target, editedCard);
        displayedCards.setCards(cards);
    }

    /**
     * Removes {@code key} from this {@code currentDeck}.
     * {@code key} must exist in the currentDeck.
     */
    public void removeCard(AnakinCard key) {
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        cards.remove(key);
        displayedCards.setCards(cards);
    }

    //// util methods

    @Override
    public String toString() {
        return decks.asUnmodifiableObservableList().size() + " decks";
        // TODO: refine later
    }

    @Override
    public ObservableList<AnakinDeck> getDeckList() {
        return decks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<AnakinCard> getCardList() {
        return displayedCards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Anakin // instanceof handles nulls
                && decks.equals(((Anakin) other).decks));
    }

    @Override
    public int hashCode() {
        return decks.hashCode();
    }
}
