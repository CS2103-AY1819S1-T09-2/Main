package seedu.address.model.anakindeck;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.anakincommands.CommandTestUtil.VALID_ANSWER_A;
import static seedu.address.logic.anakincommands.CommandTestUtil.VALID_QUESTION_A;
import static seedu.address.testutil.TypicalCards.CARD_A;
import static seedu.address.testutil.TypicalCards.CARD_B;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.CardBuilder;

public class CardTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isSameCard() {
        // same object -> returns true
        assertTrue(CARD_A.isSameCard(CARD_A));

        // null -> returns false
        assertFalse(CARD_A.isSameCard(null));

        // different question -> returns false
        Card editedDeckA = new CardBuilder(CARD_A).withQuestion(VALID_QUESTION_A).build();
        assertFalse(CARD_A.isSameCard(editedDeckA));

        // same question, different answers -> returns true
        editedDeckA = new CardBuilder(CARD_A).withAnswer(VALID_ANSWER_A).build();
        assertTrue(CARD_A.isSameCard(editedDeckA));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Card cardCopy = new CardBuilder(CARD_A).build();
        assertTrue(CARD_A.equals(cardCopy));

        // same object -> returns true
        assertTrue(CARD_A.equals(CARD_A));

        // null -> returns false
        assertFalse(CARD_A.equals(null));

        // different type -> returns false
        assertFalse(CARD_A.equals(5));

        // different card -> returns false
        assertFalse(CARD_A.equals(CARD_B));
    }
}
