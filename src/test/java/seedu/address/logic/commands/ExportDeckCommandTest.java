package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showDeckAtIndex;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakin;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DECK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DECK;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.anakinexceptions.DeckImportException;
import seedu.address.storage.portmanager.PortManager;
import seedu.address.storage.portmanager.Porter;

/**
 * Contains integration tests (interaction with the Anakin) and unit tests for
 * {@code ExportDeckCommand}.
 */
public class ExportDeckCommandTest {

    private Model model = new ModelManager(getTypicalAnakin(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    //@Test
    // @Todo Find out why this doesn't work on TRAVIS
    //    public void execute_validIndexUnfilteredList_success() {
    //        Deck deckToExport = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
    //        ExportDeckCommand exportCommand = new ExportDeckCommand(INDEX_FIRST_DECK);
    //        Porter temp = new PortManagerExportsDeck();
    //
    //        String location = temp.exportDeck(deckToExport);
    //
    //        String expectedMessage = String.format(ExportDeckCommand.MESSAGE_EXPORT_DECK_SUCCESS, deckToExport,
    // location);
    //
    //        ModelManager expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
    //        expectedModel.exportDeck(deckToExport);
    //        expectedModel.commitAnakin();
    //
    //        assertCommandSuccess(exportCommand, model, commandHistory, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        ExportDeckCommand exportCommand = new ExportDeckCommand(outOfBoundIndex);

        assertCommandFailure(exportCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    //@Test
    // @Todo Find out why this doesn't work on TRAVIS
    //    public void execute_validIndexFilteredList_success() {
    //        showDeckAtIndex(model, INDEX_FIRST_DECK);
    //
    //        Deck deckToExport = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
    //        ExportDeckCommand exportCommand = new ExportDeckCommand(INDEX_FIRST_DECK);
    //
    //        PortManager temp = new PortManager();
    //        String location = temp.getBfp() + "\\" + deckToExport.getName().fullName + ".xml";
    //        String expectedMessage = String.format(ExportDeckCommand.MESSAGE_EXPORT_DECK_SUCCESS, deckToExport,
    // location);
    //
    //        Model expectedModel = new ModelManager(model.getAnakin(), new UserPrefs());
    //        showDeckAtIndex(expectedModel, INDEX_FIRST_DECK);
    //        expectedModel.exportDeck(deckToExport);
    //        expectedModel.commitAnakin();
    //
    //        assertCommandSuccess(exportCommand, model, commandHistory, expectedMessage, expectedModel);
    //    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        Index outOfBoundIndex = INDEX_SECOND_DECK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAnakin().getDeckList().size());

        ExportDeckCommand exportCommand = new ExportDeckCommand(outOfBoundIndex);

        assertCommandFailure(exportCommand, model, commandHistory,
            Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ExportDeckCommand exportFirstCommand = new ExportDeckCommand(INDEX_FIRST_DECK);
        ExportDeckCommand exportSecondCommand = new ExportDeckCommand(INDEX_SECOND_DECK);

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        ExportDeckCommand exportFirstCommandCopy = new ExportDeckCommand(INDEX_FIRST_DECK);
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different deck -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }


    private static class PortManagerExportsDeck implements Porter {

        @Override
        public String exportDeck(Deck deck) {
            PortManager temp = new PortManager();
            return temp.getBfp() + "\\" + deck.getName().fullName + ".xml";
        }

        @Override
        public Deck importDeck(String stringPath) throws DeckImportException {
            throw new AssertionError("This method should not be called.");
        }
    }
}
