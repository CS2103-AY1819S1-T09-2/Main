package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardCardDisplaysCard;

import org.junit.Test;

import guitests.guihandles.CardCardHandle;
import seedu.address.model.anakindeck.Card;
import seedu.address.testutil.AnakinCardBuilder;

public class CardCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Card card = new AnakinCardBuilder().build();
        CardCard uiCard = new CardCard(card, 1);
        uiPartRule.setUiPart(uiCard);
        assertCardDisplay(uiCard, card, 1);
    }

    @Test
    public void equals() {
        Card card = new AnakinCardBuilder().build();
        CardCard uiCard = new CardCard(card, 0);

        // same card, same index -> returns true
        CardCard copy = new CardCard(card, 0);
        assertTrue(uiCard.equals(copy));

        // same object -> returns true
        assertTrue(uiCard.equals(uiCard));

        // null -> returns false
        assertFalse(uiCard.equals(null));

        // different types -> returns false
        assertFalse(uiCard.equals(0));

        // different card, same index -> returns false
        Card differentCard = new AnakinCardBuilder().withQuestion("differentQuestion")
                .withAnswer("differentAnswer").build();
        assertFalse(uiCard.equals(new CardCard(differentCard, 0)));

        // same card, different index -> returns false
        assertFalse(uiCard.equals(new CardCard(card, 1)));
    }

    /**
     * Asserts that {@code uiCard} displays the details of {@code expectedCard} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(CardCard uiCard, Card expectedCard, int expectedId) {
        guiRobot.pauseForHuman();

        CardCardHandle cardCardHandle = new CardCardHandle(uiCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", cardCardHandle.getId());

        // verify card details are displayed correctly
        assertCardCardDisplaysCard(expectedCard, cardCardHandle);
    }
}
