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
 * {@code ChangeDeckCommand}.
 */
public class ChangeDeckCommandTest {

    private Model model = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Deck deckToEnter = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        ChangeDeckCommand cdCommand = new ChangeDeckCommand(INDEX_FIRST_DECK);

        String expectedMessage = String.format(ChangeDeckCommand.MESSAGE_CD_SUCCESS, deckToEnter);

        ModelManager expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.goIntoDeck(deckToEnter);
        expectedModel.commitAnakin();

        assertCommandSuccess(cdCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        ChangeDeckCommand cdCommand = new ChangeDeckCommand(outOfBoundIndex);

        assertCommandFailure(cdCommand, model, commandHistory, AddressbookMessages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showDeckAtIndex(model, INDEX_FIRST_DECK);
//
//        Deck deckToEnter = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
//        ChangeDeckCommand cdCommand = new ChangeDeckCommand(INDEX_FIRST_DECK);
//
//        String expectedMessage = String.format(ChangeDeckCommand.MESSAGE_CD_SUCCESS, deckToEnter);
//
//        Model expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
//        expectedModel.goIntoDeck(deckToEnter);
//        expectedModel.commitAnakin();
//
//        assertCommandSuccess(cdCommand, model, commandHistory, expectedMessage, expectedModel);
//    }

    @Test
    public void execute_validLeaveDeck_success(){
        Model executedModel = new ModelManager(model.getAnakin(),new UserPrefs());
        Deck deckToEnter = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        ChangeDeckCommand cdCommand = new ChangeDeckCommand();

        String expectedMessage = String.format(ChangeDeckCommand.MESSAGE_EXIT_SUCCESS);

        // Enter deck so that cdCommand can leave it
        executedModel.goIntoDeck(deckToEnter);

        Model expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.commitAnakin();

        assertCommandSuccess(cdCommand, executedModel, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLeaveDeck_throwsCommandException(){
        ChangeDeckCommand cdCommand = new ChangeDeckCommand();

        String expectedException = String.format(AddressbookMessages.MESSAGE_NOT_INSIDE_DECK);

        assertCommandFailure(cdCommand, model, commandHistory, expectedException);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        Index outOfBoundIndex = INDEX_SECOND_DECK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAnakin().getDeckList().size());

        ChangeDeckCommand cdCommand = new ChangeDeckCommand(outOfBoundIndex);

        assertCommandFailure(cdCommand, model, commandHistory, AddressbookMessages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ChangeDeckCommand cdFirstCommand = new ChangeDeckCommand(INDEX_FIRST_DECK);
        ChangeDeckCommand cdSecondCommand = new ChangeDeckCommand(INDEX_SECOND_DECK);

        // same object -> returns true
        assertTrue(cdFirstCommand.equals(cdFirstCommand));

        // same values -> returns true
        ChangeDeckCommand cdFirstCommandCopy = new ChangeDeckCommand(INDEX_FIRST_DECK);
        assertTrue(cdFirstCommand.equals(cdFirstCommandCopy));

        // different types -> returns false
        assertFalse(cdFirstCommand.equals(1));

        // null -> returns false
        assertFalse(cdFirstCommand.equals(null));

        // different deck -> returns false
        assertFalse(cdFirstCommand.equals(cdSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDeck(Model model) {
        model.updateFilteredDeckList(p -> false);

        assertTrue(model.getFilteredDeckList().isEmpty());
    }
}
