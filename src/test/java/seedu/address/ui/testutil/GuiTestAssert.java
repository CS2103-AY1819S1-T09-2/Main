package seedu.address.ui.testutil;

import static org.junit.Assert.assertEquals;

import java.util.List;

import guitests.guihandles.CardCardHandle;
import guitests.guihandles.CardListPanelHandle;
import guitests.guihandles.DeckCardHandle;
import guitests.guihandles.DeckListPanelHandle;
import guitests.guihandles.ResultDisplayHandle;
import seedu.address.model.anakindeck.AnakinCard;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * A set of assertion methods useful for writing GUI tests.
 */
public class GuiTestAssert {
    /**
     * Asserts that {@code actualDeckCard} displays the same values as {@code expectedDeckCard}.
     */
    public static void assertDeckCardEquals(DeckCardHandle expectedDeckCard, DeckCardHandle actualDeckCard) {
        assertEquals(expectedDeckCard.getId(), actualDeckCard.getId());
        assertEquals(expectedDeckCard.getName(), actualDeckCard.getName());
    }

    /**
     * Asserts that {@code actualDeckCard} displays the details of {@code expectedDeck}.
     */
    public static void assertDeckCardDisplaysDeck(AnakinDeck expectedDeck, DeckCardHandle actualCard) {
        assertEquals(expectedDeck.getName().fullName, actualCard.getName());
    }

    /**
     * Asserts that the list in {@code deckListPanelHandle} displays the details of {@code decks} correctly and
     * in the correct order.
     */
    public static void assertDeckListMatching(DeckListPanelHandle deckListPanelHandle, AnakinDeck... decks) {
        for (int i = 0; i < decks.length; i++) {
            deckListPanelHandle.navigateToCard(i);
            assertDeckCardDisplaysDeck(decks[i], deckListPanelHandle.getDeckCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code deckListPanelHandle} displays the details of {@code decks} correctly and
     * in the correct order.
     */
    public static void assertDeckListMatching(DeckListPanelHandle deckListPanelHandle, List<AnakinDeck> decks) {
        assertDeckListMatching(deckListPanelHandle, decks.toArray(new AnakinDeck[0]));
    }

    /**
     * Asserts the size of the list in {@code deckListPanelHandle} equals to {@code size}.
     */
    public static void assertDeckListSize(DeckListPanelHandle deckListPanelHandle, int size) {
        int numberOfDecks = deckListPanelHandle.getListSize();
        assertEquals(size, numberOfDecks);
    }

    /**
     * Asserts that {@code actualCardCard} displays the same values as {@code expectedCardCard}.
     */
    public static void assertCardCardEquals(CardCardHandle expectedCard, CardCardHandle actualCard) {
        assertEquals(expectedCard.getId(), actualCard.getId());
        assertEquals(expectedCard.getQuestion(), actualCard.getQuestion());
    }

    /**
     * Asserts that {@code actualCardCard} displays the details of {@code expectedCard}.
     */
    public static void assertCardCardDisplaysCard(AnakinCard expectedCard, CardCardHandle actualCard) {
        assertEquals(expectedCard.getQuestion().fullQuestion, actualCard.getQuestion());
    }

    /**
     * Asserts that the list in {@code cardListPanelHandle} displays the details of {@code cards} correctly and
     * in the correct order.
     */
    public static void assertCardListMatching(CardListPanelHandle cardListPanelHandle, AnakinCard... cards) {
        for (int i = 0; i < cards.length; i++) {
            cardListPanelHandle.navigateToCard(i);
            assertCardCardDisplaysCard(cards[i], cardListPanelHandle.getCardCardHandle(i));
        }
    }

    /**
     * Asserts that the list in {@code cardListPanelHandle} displays the details of {@code cards} correctly and
     * in the correct order.
     */
    public static void assertCardListMatching(CardListPanelHandle cardListPanelHandle, List<AnakinCard> cards) {
        assertCardListMatching(cardListPanelHandle, cards.toArray(new AnakinCard[0]));
    }

    /**
     * Asserts the size of the list in {@code cardListPanelHandle} equals to {@code size}.
     */
    public static void assertCardListSize(CardListPanelHandle cardListPanelHandle, int size) {
        int numberOfCards = cardListPanelHandle.getListSize();
        assertEquals(size, numberOfCards);
    }

    /**
     * Asserts the message shown in {@code resultDisplayHandle} equals to {@code expected}.
     */
    public static void assertResultMessage(ResultDisplayHandle resultDisplayHandle, String expected) {
        assertEquals(expected, resultDisplayHandle.getText());
    }
}
