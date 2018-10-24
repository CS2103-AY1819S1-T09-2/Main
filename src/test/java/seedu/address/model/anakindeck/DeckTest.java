package seedu.address.model.anakindeck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_CARD_LIST;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_NAME_DECK_A;
import static seedu.address.testutil.AnakinTypicalCards.CARD_A;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_A;
import static seedu.address.testutil.AnakinTypicalDecks.DECK_B;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import seedu.address.testutil.AnakinDeckBuilder;

public class DeckTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Deck deck = new AnakinDeckBuilder().build();
        thrown.expect(UnsupportedOperationException.class);
        deck.getCards().remove(CARD_A);
    }

    @Test
    public void isSameDeck() {
        // same object -> returns true
        assertTrue(DECK_A.isSameDeck(DECK_A));

        // null -> returns false
        assertFalse(DECK_A.isSameDeck(null));

        // different name -> returns false
        Deck editedDeck_A = new AnakinDeckBuilder(DECK_A).withName(VALID_NAME_DECK_A).build();
        assertFalse(DECK_A.isSameDeck(editedDeck_A));

        // same name, different attributes -> returns true
        editedDeck_A = new AnakinDeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        assertTrue(DECK_A.isSameDeck(editedDeck_A));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deck aliceCopy = new AnakinDeckBuilder(DECK_A).build();
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
        Deck editedDeck_A = new AnakinDeckBuilder(DECK_A).withName(VALID_NAME_DECK_A).build();
        assertFalse(DECK_A.equals(editedDeck_A));

        // different cardList -> returns false
        editedDeck_A = new AnakinDeckBuilder(DECK_A).withCards(VALID_CARD_LIST).build();
        assertFalse(DECK_A.equals(editedDeck_A));
    }
}
