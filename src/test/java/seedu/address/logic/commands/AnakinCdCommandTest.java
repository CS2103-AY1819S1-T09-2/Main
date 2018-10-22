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
import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.AnakinCdCommand;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.anakindeck.AnakinDeck;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AnakinCdCommand}.
 */
public class AnakinCdCommandTest {

    private AnakinModel model = new AnakinModelManager(getTypicalAnakin(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        AnakinDeck deckToEnter = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        AnakinCdCommand cdCommand = new AnakinCdCommand(INDEX_FIRST_DECK);

        String expectedMessage = String.format(AnakinCdCommand.MESSAGE_CD_SUCCESS, deckToEnter);

        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.goIntoDeck(deckToEnter);
        expectedModel.commitAnakin();

        assertCommandSuccess(cdCommand, model, commandHistory, expectedMessage, expectedModel);
    }
    

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        AnakinCdCommand cdCommand = new AnakinCdCommand(outOfBoundIndex);

        assertCommandFailure(cdCommand, model, commandHistory, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

//    @Test
//    public void execute_validIndexFilteredList_success() {
//        showDeckAtIndex(model, INDEX_FIRST_DECK);
//
//        AnakinDeck deckToEnter = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
//        AnakinCdCommand cdCommand = new AnakinCdCommand(INDEX_FIRST_DECK);
//
//        String expectedMessage = String.format(AnakinCdCommand.MESSAGE_CD_SUCCESS, deckToEnter);
//
//        AnakinModel expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
//        expectedModel.goIntoDeck(deckToEnter);
//        expectedModel.commitAnakin();
//
//        assertCommandSuccess(cdCommand, model, commandHistory, expectedMessage, expectedModel);
//    }

    @Test
    public void execute_validLeaveDeck_success(){
        AnakinModel executedModel = new AnakinModelManager(model.getAnakin(),new UserPrefs());
        AnakinDeck deckToEnter = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        AnakinCdCommand cdCommand = new AnakinCdCommand();

        String expectedMessage = String.format(AnakinCdCommand.MESSAGE_EXIT_SUCCESS);

        // Enter deck so that cdCommand can leave it
        executedModel.goIntoDeck(deckToEnter);

        AnakinModel expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.commitAnakin();

        assertCommandSuccess(cdCommand, executedModel, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLeaveDeck_throwsCommandException(){
        AnakinCdCommand cdCommand = new AnakinCdCommand();

        String expectedException = String.format(Messages.MESSAGE_NOT_INSIDE_DECK);

        assertCommandFailure(cdCommand, model, commandHistory, expectedException);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        Index outOfBoundIndex = INDEX_SECOND_DECK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAnakin().getDeckList().size());

        AnakinCdCommand cdCommand = new AnakinCdCommand(outOfBoundIndex);

        assertCommandFailure(cdCommand, model, commandHistory, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        AnakinCdCommand cdFirstCommand = new AnakinCdCommand(INDEX_FIRST_DECK);
        AnakinCdCommand cdSecondCommand = new AnakinCdCommand(INDEX_SECOND_DECK);

        // same object -> returns true
        assertTrue(cdFirstCommand.equals(cdFirstCommand));

        // same values -> returns true
        AnakinCdCommand cdFirstCommandCopy = new AnakinCdCommand(INDEX_FIRST_DECK);
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
    private void showNoDeck(AnakinModel model) {
        model.updateFilteredDeckList(p -> false);

        assertTrue(model.getFilteredDeckList().isEmpty());
    }
}
