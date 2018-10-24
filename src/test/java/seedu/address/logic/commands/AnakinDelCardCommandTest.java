package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakinInDeck;
import static seedu.address.testutil.AnakinTypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.AnakinTypicalIndexes.INDEX_SECOND_CARD;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.AnakinDelCardCommand;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.anakindeck.AnakinCard;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AnakinDelCardCommand}.
 */
public class AnakinDelCardCommandTest {

    private AnakinModel model = new AnakinModelManager(getTypicalAnakinInDeck(), new UserPrefs());

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        AnakinCard cardToDelete = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        AnakinDelCardCommand deleteCommand = new AnakinDelCardCommand(INDEX_FIRST_CARD);

        String expectedMessage = String.format(AnakinDelCardCommand.MESSAGE_DELETE_CARD_SUCCESS, cardToDelete);

        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.deleteCard(cardToDelete);
        expectedModel.commitAnakin();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        AnakinDelCardCommand deleteCommand = new AnakinDelCardCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        //showCardAtIndex(model, INDEX_FIRST_CARD);

        AnakinCard cardToDelete = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        AnakinDelCardCommand deleteCommand = new AnakinDelCardCommand(INDEX_FIRST_CARD);

        String expectedMessage = String.format(AnakinDelCardCommand.MESSAGE_DELETE_CARD_SUCCESS, cardToDelete);

        AnakinModel expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.deleteCard(cardToDelete);
        expectedModel.commitAnakin();
        showNoCard(expectedModel);

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        AnakinDelCardCommand deleteFirstCommand = new AnakinDelCardCommand(INDEX_FIRST_CARD);
        AnakinDelCardCommand deleteSecondCommand = new AnakinDelCardCommand(INDEX_SECOND_CARD);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        AnakinDelCardCommand deleteFirstCommandCopy = new AnakinDelCardCommand(INDEX_FIRST_CARD);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(0));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different card -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCard(AnakinModel model) {
        model.updateFilteredCardList(p -> false);

        assertTrue(model.getFilteredCardList().isEmpty());
    }
}
