package seedu.address.model.deck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CARD_LIST;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DECK_A;
import static seedu.address.testutil.TypicalCards.CARD_A;
import static seedu.address.testutil.TypicalDecks.DECK_A;
import static seedu.address.testutil.TypicalDecks.DECK_B;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.model.deck.anakinexceptions.CardNotFoundException;
import seedu.address.testutil.DeckBuilder;

public class DeckTest {

    @Test
    public void isSameDeck() {
        // same object -> returns true
        assertTrue(DECK_A.isSameDeck(DECK_A));

        // null -> returns false
        assertFalse(DECK_A.isSameDeck(null));

        // different name -> returns false
        Deck editedDeckA = new DeckBuilder(DECK_A).withName(VALID_NAME_DECK_A).build();
        assertFalse(DECK_A.isSameDeck(editedDeckA));

        // same name, different attributes -> returns true
        editedDeckA = new DeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        assertTrue(DECK_A.isSameDeck(editedDeckA));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deck aliceCopy = new DeckBuilder(DECK_A).build();
        assertTrue(DECK_A.equals(aliceCopy));

        // same object -> returns true
        assertTrue(DECK_A.equals(DECK_A));

        // null -> returns false
        assertFalse(DECK_A.equals(null));

        // different type -> returns false
        assertFalse(DECK_A.equals(5));

        // different deck -> returns false
        assertFalse(DECK_A.equals(DECK_B));

        // different name -> returns false
        Deck editedDeckA = new DeckBuilder(DECK_A).withName(VALID_NAME_DECK_A).build();
        assertFalse(DECK_A.equals(editedDeckA));

        // same name, different cardList -> returns true
        editedDeckA = new DeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        assertTrue(DECK_A.equals(editedDeckA));
    }
}
