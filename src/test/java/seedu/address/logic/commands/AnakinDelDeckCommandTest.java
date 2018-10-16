package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AnakinCommandTestUtil.showDeckAtIndex;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakin;
import static seedu.address.testutil.AnakinTypicalIndexes.INDEX_FIRST_DECK;
import static seedu.address.testutil.AnakinTypicalIndexes.INDEX_SECOND_DECK;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.AnakinCommands.AnakinDelDeckCommand;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AnakinDelDeckCommand}.
 */
public class AnakinDelDeckCommandTest {

    private AnakinModel model = new AnakinModelManager(getTypicalAnakin(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        AnakinDeck deckToDelete = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        AnakinDelDeckCommand deleteCommand = new AnakinDelDeckCommand(INDEX_FIRST_DECK);

        String expectedMessage = String.format(AnakinDelDeckCommand.MESSAGE_DELETE_DECK_SUCCESS, deckToDelete);

        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.deleteDeck(deckToDelete);
        expectedModel.commitAnakin();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        AnakinDelDeckCommand deleteCommand = new AnakinDelDeckCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        AnakinDeck deckToDelete = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        AnakinDelDeckCommand deleteCommand = new AnakinDelDeckCommand(INDEX_FIRST_DECK);

        String expectedMessage = String.format(AnakinDelDeckCommand.MESSAGE_DELETE_DECK_SUCCESS, deckToDelete);

        AnakinModel expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.deleteDeck(deckToDelete);
        expectedModel.commitAnakin();
        showNoDeck(expectedModel);

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        Index outOfBoundIndex = INDEX_SECOND_DECK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAnakin().getDeckList().size());

        AnakinDelDeckCommand deleteCommand = new AnakinDelDeckCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AnakinDelDeckCommand deleteFirstCommand = new AnakinDelDeckCommand(INDEX_FIRST_DECK);
        AnakinDelDeckCommand deleteSecondCommand = new AnakinDelDeckCommand(INDEX_SECOND_DECK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        AnakinDelDeckCommand deleteFirstCommandCopy = new AnakinDelDeckCommand(INDEX_FIRST_DECK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different deck -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDeck(AnakinModel model) {
        model.updateFilteredDeckList(p -> false);

        assertTrue(model.getFilteredDeckList().isEmpty());
    }
}
