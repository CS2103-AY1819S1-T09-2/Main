package systemtests;

import static org.junit.Assert.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_DECKS_LISTED_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.TypicalDecks.DECK_B;
import static seedu.address.testutil.TypicalDecks.DECK_C;
import static seedu.address.testutil.TypicalDecks.DECK_D;
import static seedu.address.testutil.TypicalDecks.DECK_G;
import static seedu.address.testutil.TypicalDecks.DECK_H;
import static seedu.address.testutil.TypicalDecks.KEYWORD_MATCHING_JOHN;

import org.junit.Test;

import seedu.address.logic.commands.DeleteDeckCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;


public class FindCommandSystemTest extends AnakinSystemTest {

    @Test
    public void find() {
        /* Case: find multiple decks in Anakin, command with leading spaces and trailing spaces
         * -> 2 decks found
         */
        String command = "   " + FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_JOHN + "   ";
        Model expectedModel = getModel();
        ModelHelper.setFilteredDeckList(expectedModel, DECK_G, DECK_H); // first names of Deck_G and Deck_H are John
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: repeat previous find command where deck list is displaying the decks we are finding
         * -> 2 decks found
         */
        command = FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_JOHN;
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find deck where deck list is not displaying the deck we are finding -> 1 deck found */
        command = FindCommand.COMMAND_WORD + " Calculus";
        ModelHelper.setFilteredDeckList(expectedModel, DECK_C);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple decks in Anakin, 2 keywords -> 2 decks found */
        command = FindCommand.COMMAND_WORD + " Bacon Calculus";
        ModelHelper.setFilteredDeckList(expectedModel, DECK_B, DECK_C);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple decks in Anakin, 2 keywords in reversed order -> 2 decks found */
        command = FindCommand.COMMAND_WORD + " Calculus Bacon";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple decks in Anakin, 2 keywords with 1 repeat -> 2 decks found */
        command = FindCommand.COMMAND_WORD + " Calculus Bacon Calculus";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find multiple decks in Anakin, 2 matching keywords and 1 non-matching keyword
         * -> 2 decks found
         */
        command = FindCommand.COMMAND_WORD + " Calculus Bacon NonMatchingKeyWord";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: undo previous find command -> rejected */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: redo previous find command -> rejected */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_FAILURE;
        assertCommandFailure(command, expectedResultMessage);

        /* Case: find same decks in Anakin after deleting 1 of them -> 1 deck found */
        executeCommand(DeleteDeckCommand.COMMAND_WORD + " 1");
        assertFalse(getModel().getAnakin().getDeckList().contains(DECK_G));
        command = FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_JOHN;
        expectedModel = getModel();
        ModelHelper.setFilteredDeckList(expectedModel, DECK_H);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find deck in Anakin, keyword is same as name but of different case -> 1 decks found */
        command = FindCommand.COMMAND_WORD + " JoHn";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find decks in Anakin, keyword is substring of name -> 0 decks found */
        command = FindCommand.COMMAND_WORD + " Joh";
        ModelHelper.setFilteredDeckList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find decks in Anakin, name is substring of keyword -> 0 decks found */
        command = FindCommand.COMMAND_WORD + " Johnny";
        ModelHelper.setFilteredDeckList(expectedModel);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: find decks not in Anakin -> 0 decks found */
        command = FindCommand.COMMAND_WORD + " Julius";
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        // TODO: Write Card level checks for find
        //        /* Case: find phone number of decks in Anakin -> 0 decks found */
        //        command = FindCommand.COMMAND_WORD + " " + DECK_D.getPhone().value;
        //        assertCommandSuccess(command, expectedModel);
        //        assertSelectedCardUnchanged();
        //
        //        /* Case: find address of decks in Anakin -> 0 decks found */
        //        command = FindCommand.COMMAND_WORD + " " + DECK_D.getAddress().value;
        //        assertCommandSuccess(command, expectedModel);
        //        assertSelectedCardUnchanged();
        //
        //        /* Case: find email of decks in Anakin -> 0 decks found */
        //        command = FindCommand.COMMAND_WORD + " " + DECK_D.getEmail().value;
        //        assertCommandSuccess(command, expectedModel);
        //        assertSelectedCardUnchanged();
        //
        //        /* Case: find tags of decks in Anakin -> 0 decks found */
        //        List<Tag> tags = new ArrayList<>(DANIEL.getTags());
        //        command = FindCommand.COMMAND_WORD + " " + tags.get(0).tagName;
        //        assertCommandSuccess(command, expectedModel);
        //        assertSelectedCardUnchanged();


        /* Case: find decks in empty Anakin -> 0 decks found */
        deleteAllDecks();
        command = FindCommand.COMMAND_WORD + " " + KEYWORD_MATCHING_JOHN;
        expectedModel = getModel();
        ModelHelper.setFilteredDeckList(expectedModel, DECK_D);
        assertCommandSuccess(command, expectedModel);
        assertSelectedCardUnchanged();

        /* Case: mixed case command word -> rejected */
        command = "FiNd Meier";
        assertCommandFailure(command, MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Executes {@code command} and verifies that the command box displays an empty string, the result display
     * box displays {@code Messages#MESSAGE_DECKS_LISTED_OVERVIEW} with the number of deck in the
     * filtered list,
     * and the model related components equal to {@code expectedModel}.
     * These verifications are done by
     * {@code AnakinSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the status bar remains unchanged, and the command box has the default style class, and the
     * selected card updated accordingly, depending on {@code cardStatus}.
     *
     * @see AnakinSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandSuccess(String command, Model expectedModel) {
        String expectedResultMessage = String.format(
                MESSAGE_DECKS_LISTED_OVERVIEW, expectedModel.getFilteredDeckList().size());

        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchanged();
    }

    /**
     * Executes {@code command} and verifies that the command box displays {@code command}, the result display
     * box displays {@code expectedResultMessage} and the model related components equal to the current model.
     * These verifications are done by
     * {@code AnakinSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * Also verifies that the browser url, selected card and status bar remain unchanged, and the command box has the
     * error style.
     *
     * @see AnakinSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
