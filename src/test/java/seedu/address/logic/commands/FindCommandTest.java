package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_CARDS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_DECKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.ALL;
import static seedu.address.testutil.TypicalCards.IS;
import static seedu.address.testutil.TypicalCards.MILLION;
import static seedu.address.testutil.TypicalDecks.HOLDING;
import static seedu.address.testutil.TypicalDecks.NOTHING;
import static seedu.address.testutil.TypicalDecks.THERE;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakin;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakinInDeck;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.CardQuestionContainsKeywordsPredicate;
import seedu.address.model.deck.DeckNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        DeckNameContainsKeywordsPredicate firstDeckPredicate =
                new DeckNameContainsKeywordsPredicate(Collections.singletonList("firstDeck"));
        CardQuestionContainsKeywordsPredicate firstCardPredicate =
                new CardQuestionContainsKeywordsPredicate(Collections.singletonList("firstCard"));
        DeckNameContainsKeywordsPredicate secondDeckPredicate =
                new DeckNameContainsKeywordsPredicate(Collections.singletonList("secondDeck"));
        CardQuestionContainsKeywordsPredicate secondCardPredicate =
                new CardQuestionContainsKeywordsPredicate(Collections.singletonList("secondCard"));

        //// Find deck

        FindCommand findFirstDeckCommand = new FindCommand(firstDeckPredicate);
        FindCommand findSecondDeckCommand = new FindCommand(secondDeckPredicate);

        // same object -> returns true
        assertTrue(findFirstDeckCommand.equals(findFirstDeckCommand));

        // same values -> returns true
        FindCommand findFirstDeckCommandCopy = new FindCommand(firstDeckPredicate);
        assertTrue(findFirstDeckCommand.equals(findFirstDeckCommandCopy));

        // different types -> returns false
        assertFalse(findFirstDeckCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstDeckCommand.equals(null));

        // different deck -> returns false
        assertFalse(findFirstDeckCommand.equals(findSecondDeckCommand));

        //// Find card

        FindCommand findFirstCardCommand = new FindCommand(firstCardPredicate);
        FindCommand findSecondCardCommand = new FindCommand(secondCardPredicate);

        // same object -> returns true
        assertTrue(findFirstCardCommand.equals(findFirstCardCommand));

        // same values -> returns true
        FindCommand findFirstCardCommandCopy = new FindCommand(firstCardPredicate);
        assertTrue(findFirstCardCommand.equals(findFirstCardCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCardCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCardCommand.equals(null));

        // different card -> returns false
        assertFalse(findFirstCardCommand.equals(findSecondCardCommand));
    }

    @Test
    public void executeCommandInReview_throwsCommandException() {
        Model actualModel = new ModelManager(getTypicalAnakinInDeck(), new UserPrefs());
        actualModel.startReview();
        DeckNameContainsKeywordsPredicate deckPredicate = prepareDeckPredicate(" ");
        FindCommand command = new FindCommand(deckPredicate);
        assertCommandFailure(command, actualModel, commandHistory,
                Messages.MESSAGE_CURRENTLY_REVIEWING_DECK);
    }

    @Test
    public void executeZeroKeywordsNoDeckFound() {
        String expectedMessage = String.format(MESSAGE_DECKS_LISTED_OVERVIEW, 0);
        DeckNameContainsKeywordsPredicate deckPredicate = prepareDeckPredicate(" ");
        FindCommand command = new FindCommand(deckPredicate);
        expectedModel.updateFilteredDeckList(deckPredicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeckList());
    }

    @Test
    public void executeZeroKeywordsNoCardFound() {
        // Get into a deck in model and expectedMode
        model.getIntoDeck(THERE);
        expectedModel.getIntoDeck(THERE);

        String expectedMessage = String.format(MESSAGE_CARDS_LISTED_OVERVIEW, 0);
        CardQuestionContainsKeywordsPredicate cardPredicate = prepareCardPredicate(" ");
        FindCommand command = new FindCommand(cardPredicate);
        expectedModel.updateFilteredCardList(cardPredicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCardList());
    }

    @Test
    public void executeMultipleKeywordsMultipleDecksFound() {
        String expectedMessage = String.format(MESSAGE_DECKS_LISTED_OVERVIEW, 3);
        DeckNameContainsKeywordsPredicate deckPredicate = prepareDeckPredicate("baby is back");
        FindCommand command = new FindCommand(deckPredicate);
        expectedModel.updateFilteredDeckList(deckPredicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(THERE, NOTHING, HOLDING), model.getFilteredDeckList());
    }

    @Test
    public void executeMultipleKeywordsMultipleCardsFound() {
        // Get into a deck in model and expectedMode
        model.getIntoDeck(THERE);
        expectedModel.getIntoDeck(THERE);

        String expectedMessage = String.format(MESSAGE_CARDS_LISTED_OVERVIEW, 3);
        CardQuestionContainsKeywordsPredicate cardPredicate = prepareCardPredicate("million dreams gonna");
        FindCommand command = new FindCommand(cardPredicate);
        expectedModel.updateFilteredCardList(cardPredicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MILLION, IS, ALL), model.getFilteredCardList());
    }

    /**
     * Parses {@code userInput} into a {@code DeckNameContainsKeywordsPredicate}.
     */
    private DeckNameContainsKeywordsPredicate prepareDeckPredicate(String userInput) {
        return new DeckNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code CardQuestionContainsKeywordsPredicate}.
     */
    private CardQuestionContainsKeywordsPredicate prepareCardPredicate(String userInput) {
        return new CardQuestionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
