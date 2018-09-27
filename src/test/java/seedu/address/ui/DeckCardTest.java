package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;

import org.junit.Test;

import guitests.guihandles.PersonCardHandle;
import seedu.address.model.person.Deck;
import seedu.address.testutil.PersonBuilder;

public class DeckCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Deck deckWithNoTags = new PersonBuilder().withTags(new String[0]).build();
        PersonCard personCard = new PersonCard(deckWithNoTags, 1);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, deckWithNoTags, 1);

        // with tags
        Deck deckWithTags = new PersonBuilder().build();
        personCard = new PersonCard(deckWithTags, 2);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, deckWithTags, 2);
    }

    @Test
    public void equals() {
        Deck deck = new PersonBuilder().build();
        PersonCard personCard = new PersonCard(deck, 0);

        // same deck, same index -> returns true
        PersonCard copy = new PersonCard(deck, 0);
        assertTrue(personCard.equals(copy));

        // same object -> returns true
        assertTrue(personCard.equals(personCard));

        // null -> returns false
        assertFalse(personCard.equals(null));

        // different types -> returns false
        assertFalse(personCard.equals(0));

        // different deck, same index -> returns false
        Deck differentDeck = new PersonBuilder().withName("differentName").build();
        assertFalse(personCard.equals(new PersonCard(differentDeck, 0)));

        // same deck, different index -> returns false
        assertFalse(personCard.equals(new PersonCard(deck, 1)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedDeck} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(PersonCard personCard, Deck expectedDeck, int expectedId) {
        guiRobot.pauseForHuman();

        PersonCardHandle personCardHandle = new PersonCardHandle(personCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", personCardHandle.getId());

        // verify deck details are displayed correctly
        assertCardDisplaysPerson(expectedDeck, personCardHandle);
    }
}
