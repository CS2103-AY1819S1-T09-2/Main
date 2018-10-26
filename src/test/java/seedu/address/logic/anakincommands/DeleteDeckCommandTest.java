package seedu.address.logic.anakincommands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.anakincommands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.anakincommands.CommandTestUtil.showDeckAtIndex;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakin;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DECK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DECK;

import org.junit.Test;

import seedu.address.commons.core.AddressbookMessages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.anakindeck.Deck;

/**
 * Contains integration tests (interaction with the AddressbookModel) and unit tests for
 * {@code DeleteDeckCommand}.
 */
public class DeleteDeckCommandTest {

    private Model model = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Deck deckToDelete = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        DeleteDeckCommand deleteCommand = new DeleteDeckCommand(INDEX_FIRST_DECK);

        String expectedMessage = String.format(DeleteDeckCommand.MESSAGE_DELETE_DECK_SUCCESS, deckToDelete);

        ModelManager expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.deleteDeck(deckToDelete);
        expectedModel.commitAnakin();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        DeleteDeckCommand deleteCommand = new DeleteDeckCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        Deck deckToDelete = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        DeleteDeckCommand deleteCommand = new DeleteDeckCommand(INDEX_FIRST_DECK);

        String expectedMessage = String.format(DeleteDeckCommand.MESSAGE_DELETE_DECK_SUCCESS, deckToDelete);

        Model expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
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

        DeleteDeckCommand deleteCommand = new DeleteDeckCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteDeckCommand deleteFirstCommand = new DeleteDeckCommand(INDEX_FIRST_DECK);
        DeleteDeckCommand deleteSecondCommand = new DeleteDeckCommand(INDEX_SECOND_DECK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDeckCommand deleteFirstCommandCopy = new DeleteDeckCommand(INDEX_FIRST_DECK);
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
    private void showNoDeck(Model model) {
        model.updateFilteredDeckList(p -> false);

        assertTrue(model.getFilteredDeckList().isEmpty());
    }
}
