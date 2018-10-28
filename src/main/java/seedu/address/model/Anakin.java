package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.deck.Card;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.UniqueCardList;
import seedu.address.model.deck.UniqueDeckList;
import seedu.address.model.deck.anakinexceptions.DeckNotFoundException;
import seedu.address.storage.portmanager.PortManager;

/**
 * Wraps all data at the Anakin level
 * Duplicates are not allowed (by .isSameDeck comparison)
 */
public class Anakin implements ReadOnlyAnakin {

    private final UniqueDeckList decks;

    private boolean isInsideDeck;
    // Represent the current list of cards (when user get into a deck)
    private UniqueCardList cards;

    // Represents the list of cards displayed on the UI
    private UniqueCardList displayedCards;

    // Manager to handle imports/exports
    private PortManager portManager;

    /*
    * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
    * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
    *
    * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
    *   among constructors.
    */ {
        decks = new UniqueDeckList();
        cards = new UniqueCardList();
        displayedCards = new UniqueCardList();
        portManager = new PortManager();
    }

    public Anakin() {
    }

    /**
     * Creates an Anakin using the Decks in the {@code toBeCopied}
     */
    public Anakin(ReadOnlyAnakin toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the deck list with {@code decks}.
     * {@code decks} must not contain duplicate decks.
     */
    public void setDecks(List<Deck> decks) {
        this.decks.setDecks(decks);
    }

    /**
     * Replaces the contents of the card list with {@code cards}.
     * {@code cards} must not contain duplicate cards.
     */
    public void setCards(List<Card> cards) {
        this.cards.setCards(cards);
    }

    /**
     * Resets the existing data of this {@code Anakin} with {@code newData}.
     */
    public void resetData(ReadOnlyAnakin newData) {
        requireNonNull(newData);

        setIsInsideDeck(newData.isInsideDeck());

        setDecks(newData.getDeckList());
        setCards(newData.getCardList());
        updateDisplayedCards();
    }

    /**
     * Sort the current list of decks/cards in alphabetical order.
     */
    public void sort() {
        if (isInsideDeck()) {
            cards.sort();
            updateDisplayedCards();
        } else {
            decks.sort();
        }
    }

    public void setIsInsideDeck(boolean set) {
        isInsideDeck = set;
    }

    public void updateDisplayedCards() {
        displayedCards.setCards(cards);
    }

    //// navigating operations

    /**
     * Navigating into a deck
     */
    public void getIntoDeck(Deck deck) {
        requireNonNull(deck);
        isInsideDeck = true;
        cards = deck.getCards();
        updateDisplayedCards();
    }

    /**
     * Navigating out of the current deck
     */
    public void getOutOfDeck() {
        isInsideDeck = false;
        cards = new UniqueCardList();
        updateDisplayedCards();
    }


    /**
     * Return true if user is inside a deck
     */
    @Override
    public boolean isInsideDeck() {
        return isInsideDeck;
    }

    //// deck-level operations

    /**
     * Returns true if a deck with the same identity as {@code deck} exists in Anakin.
     */
    public boolean hasDeck(Deck deck) {
        requireNonNull(deck);
        return decks.contains(deck);
    }

    /**
     * Adds a deck to the Anakin.
     * The deck must not already exist in the Anakin.
     */
    public void addDeck(Deck d) {
        decks.add(d);
    }

    /**
     * Replaces the given deck {@code target} in the list with {@code editedDeck}.
     * {@code target} must exist in the Anakin.
     * The deck identity of {@code editedDeck} must not be the same as another existing deck in the Anakin.
     */
    public void updateDeck(Deck target, Deck editedDeck) {
        requireNonNull(editedDeck);

        decks.setDeck(target, editedDeck);
    }

    /**
     * Removes {@code key} from this {@code Anakin}.
     * {@code key} must exist in the Anakin.
     */
    public void removeDeck(Deck key) {
        decks.remove(key);
    }

    //// card-level operations

    /**
     * Returns true if a card with the same identity as {@code card} exists in current deck.
     */
    public boolean hasCard(Card card) {
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
    public void addCard(Card c) {
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        cards.add(c);
        updateDisplayedCards();
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedCard}.
     * {@code target} must exist in the current deck.
     * The card identity of {@code editedCard} must not be the same as another existing card in the current deck.
     */
    public void updateCard(Card target, Card editedCard) {
        requireNonNull(editedCard);
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        cards.setCard(target, editedCard);
        updateDisplayedCards();
    }

    /**
     * Removes {@code key} from this {@code currentDeck}.
     * {@code key} must exist in the currentDeck.
     */
    public void removeCard(Card key) {
        if (!isInsideDeck()) {
            throw new DeckNotFoundException();
        }
        cards.remove(key);
        updateDisplayedCards();
    }

    //// util methods

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(decks.asUnmodifiableObservableList().size() + " decks\n");
        Iterator<Deck> iterator = decks.iterator();
        while (iterator.hasNext()) {
            Deck cur = iterator.next();
            stringBuilder.append(cur.toString() + ": ");
            Iterator<Card> cardIterator = cur.getCards().iterator();
            while (cardIterator.hasNext()) {
                stringBuilder.append(cardIterator.next().toString() + ", ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public ObservableList<Deck> getDeckList() {
        return decks.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Card> getCardList() {
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
