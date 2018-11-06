package systemtests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_NAME_A_ARGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_NAME_B_ARGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DECK_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DECK_B;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DECKS;
import static seedu.address.testutil.TypicalDecks.KEYWORD_MATCHING_JOHN;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DECK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DECK;
import static seedu.address.testutil.TypicalDecks.DECK_C;
//import static seedu.address.testutil.TypicalDecks.VALID_DECK_A;


import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDeckCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.model.Model;
import seedu.address.model.deck.Deck;
import seedu.address.model.deck.Name;
import seedu.address.testutil.DeckBuilder;
import seedu.address.testutil.DeckUtil;

public class EditCommandSystemTest extends AnakinSystemTest {

    @Test
    public void edit() {
        Model addressbookModel = getModel();

        /* ----------------- Performing edit operation while an unfiltered list is being shown
 ---------------------- */

        /* Case: edit deck, command with leading spaces, trailing spaces and multiple spaces between each field
         * -> edited
         */
        Index index = INDEX_FIRST_DECK;
        String command = " " + EditDeckCommand.COMMAND_WORD + "  " + index.getOneBased() + "  "
                + VALID_DECK_NAME_B_ARGS + "  ";
        Deck editedDeck = new DeckBuilder(VALID_DECK_B).build();
        assertCommandSuccess(command, index, editedDeck);

        /* Case: undo editing the last deck in the list -> last deck restored */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS + EditDeckCommand.COMMAND_WORD;
        assertCommandSuccess(command, addressbookModel, expectedResultMessage);

        /* Case: redo editing the last deck in the list -> last deck edited again */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS + EditDeckCommand.COMMAND_WORD;
        addressbookModel.updateDeck(
                getModel().getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased()), editedDeck);
        assertCommandSuccess(command, addressbookModel, expectedResultMessage);

        /* Case: edit a deck with new values same as existing values -> edited */
        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_B_ARGS;
        assertCommandSuccess(command, index, VALID_DECK_B);

//        /* Case: edit a deck with new values same as another deck's values but with different name -> edited */
//        assertTrue(getModel().getAnakin().getDeckList().contains(VALID_DECK_A));
//        index = INDEX_SECOND_DECK;
//        assertNotEquals(getModel().getFilteredDeckList().get(index.getZeroBased()), VALID_DECK_A);
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_A_ARGS;
        
//        editedDeck = new DeckBuilder(VALID_DECK_A).withName(VALID_NAME_DECK_A).build();
//        assertCommandSuccess(command, index, editedDeck);

//        /* Case: edit a deck with new values same as another deck's values but with different phone and email
//         * -> edited
//         */
//        index = INDEX_SECOND_DECK;
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_B_ARGS + PHONE_DESC_DECK_C +
// EMAIL_DESC_DECK_C + ADDRESS_DESC_VALID_DECK_A + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
//        editedDeck = new DeckBuilder(VALID_DECK_A).build();
//        assertCommandSuccess(command, index, editedDeck);

//        /* Case: clear tags -> cleared */
        index = INDEX_FIRST_DECK;
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_TAG.getPrefix();
        Deck deckToEdit = getModel().getFilteredDeckList().get(index.getZeroBased());
        editedDeck = new DeckBuilder(deckToEdit).build();
//        assertCommandSuccess(command, index, editedDeck);

        /* ------------------ Performing edit operation while a filtered list is being shown
 ------------------------ */

//        /* Case: filtered deck list, edit index within bounds of address book and deck list -> edited */
//        showDecksWithName(KEYWORD_MATCHING_JOHN);
//        index = INDEX_FIRST_DECK;
//        assertTrue(index.getZeroBased() < getModel().getFilteredDeckList().size());
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + " " + VALID_DECK_NAME_B_ARGS;
//        deckToEdit = getModel().getFilteredDeckList().get(index.getZeroBased());
//        editedDeck = new DeckBuilder(deckToEdit).withName(VALID_NAME_DECK_B).build();
//        assertCommandSuccess(command, index, editedDeck);

//        /* Case: filtered deck list, edit index within bounds of address book but out of bounds of deck list
//         * -> rejected
//         */
//        showDecksWithName(KEYWORD_MATCHING_JOHN);
        int invalidIndex = getModel().getAnakin().getDeckList().size();
//        assertCommandFailure(EditDeckCommand.COMMAND_WORD + " " + invalidIndex + VALID_DECK_NAME_B_ARGS,
//                Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);

        /* --------------------- Performing edit operation while a deck card is selected
 -------------------------- */

//        /* Case: selects first card in the deck list, edit a deck -> edited, card selection remains unchanged but
//         * browser url changes
//         */
//        index = INDEX_FIRST_DECK;
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_A_ARGS;
//        // this can be misleading: card selection actually remains unchanged but the
//        // browser's url is updated to reflect the new deck's name
//        assertCommandSuccess(command, index, DECK_C, index);

        /* --------------------------------- Performing invalid edit operation
 -------------------------------------- */

        /* Case: invalid index (0) -> rejected */
        assertCommandFailure(EditDeckCommand.COMMAND_WORD + " 0" + VALID_DECK_NAME_B_ARGS,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditDeckCommand.MESSAGE_USAGE));

        /* Case: invalid index (-1) -> rejected */
        assertCommandFailure(EditDeckCommand.COMMAND_WORD + " -1" + VALID_DECK_NAME_B_ARGS,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditDeckCommand.MESSAGE_USAGE));

        /* Case: invalid index (size + 1) -> rejected */
        invalidIndex = getModel().getFilteredDeckList().size() + 1;
        assertCommandFailure(EditDeckCommand.COMMAND_WORD + " " + invalidIndex + VALID_DECK_NAME_B_ARGS,
                Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);

        /* Case: missing index -> rejected */
        assertCommandFailure(EditDeckCommand.COMMAND_WORD + VALID_DECK_NAME_B_ARGS,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditDeckCommand.MESSAGE_USAGE));

//        /* Case: missing all fields -> rejected */
//        assertCommandFailure(EditDeckCommand.COMMAND_WORD + " " + INDEX_FIRST_DECK.getOneBased(),
//                EditDeckCommand.MESSAGE_NOT_EDITED);

//        /* Case: invalid question -> rejected */
//        assertCommandFailure(EditDeckCommand.COMMAND_WORD + " " + INDEX_FIRST_DECK.getOneBased(),
//                Address.MESSAGE_ADDRESS_CONSTRAINTS);
//
//        /* Case: invalid answer -> rejected */
//        assertCommandFailure(EditDeckCommand.COMMAND_WORD + " " + INDEX_FIRST_DECK.getOneBased(),
//                Tag.MESSAGE_TAG_CONSTRAINTS);

//        /* Case: edit a deck with new values same as another deck's values -> rejected */
//        executeCommand(DeckUtil.getNewDeckCommand(VALID_DECK_A));
//        assertTrue(getModel().getAnakin().getDeckList().contains(VALID_DECK_A));
//        index = INDEX_FIRST_DECK;
//        assertFalse(getModel().getFilteredDeckList().get(index.getZeroBased()).equals(VALID_DECK_A));
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_B_ARGS;
//        assertCommandFailure(command, EditDeckCommand.MESSAGE_DUPLICATE_DECK);

//        /* Case: edit a deck with new values same as another deck's values but with different tags -> rejected */
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_B_ARGS + PHONE_DESC_VALID_DECK_A +
// EMAIL_DESC_VALID_DECK_A;
//        assertCommandFailure(command, EditDeckCommand.MESSAGE_DUPLICATE_DECK);

//        /* Case: edit a deck with new values same as another deck's values but with different address ->
// rejected */
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_B_ARGS;
//        assertCommandFailure(command, EditDeckCommand.MESSAGE_DUPLICATE_DECK);
//
//        /* Case: edit a deck with new values same as another deck's values but with different phone -> rejected */
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_B_ARGS;
//        assertCommandFailure(command, EditDeckCommand.MESSAGE_DUPLICATE_DECK);

//        /* Case: edit a deck with new values same as another deck's values but with different email -> rejected */
//        command = EditDeckCommand.COMMAND_WORD + " " + index.getOneBased() + VALID_DECK_NAME_B_ARGS + PHONE_DESC_VALID_DECK_A +
// EMAIL_DESC_DECK_C;
//        assertCommandFailure(command, EditDeckCommand.MESSAGE_DUPLICATE_DECK);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Index, Deck, Index)} except that
     * the browser url and selected card remain unchanged.
     * @param toEdit the index of the current model's filtered list
     * @see EditCommandSystemTest#assertCommandSuccess(String, Index, Deck, Index)
     */
    private void assertCommandSuccess(String command, Index toEdit, Deck editedDeck) {
        assertCommandSuccess(command, toEdit, editedDeck, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String, Index)} and
 in addition,<br>
     * 1. Asserts that result display box displays the success message of executing {@code EditDeckCommand}.<br>
     * 2. Asserts that the model related components are updated to reflect the deck at index {@code toEdit} being
     * updated to values specified {@code editedDeck}.<br>
     * @param toEdit the index of the current model's filtered list.
     * @see EditCommandSystemTest#assertCommandSuccess(String, Model, String, Index)
     */
    private void assertCommandSuccess(String command, Index toEdit, Deck editedDeck,
            Index expectedSelectedCardIndex) {
        Model expectedModel = getModel();
        expectedModel.updateDeck(expectedModel.getFilteredDeckList().get(toEdit
 .getZeroBased()), editedDeck);
        expectedModel.updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);

        assertCommandSuccess(command, expectedModel,
                String.format(EditDeckCommand.MESSAGE_EDIT_DECK_SUCCESS, editedDeck), expectedSelectedCardIndex);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String, Index)}
 except that the
     * browser url and selected card remain unchanged.
     * @see EditCommandSystemTest#assertCommandSuccess(String, Model, String, Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String
 expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url and selected card update accordingly depending on the card at
     * {@code expectedSelectedCardIndex}.<br>
     * 4. Asserts that the status bar's sync status changes.<br>
     * 5. Asserts that the command box has the default style class.<br>
     * Verifications 1 and 2 are performed by
     * {@code AnakinSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     * @see AnakinSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     * @see AnakinSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String
 expectedResultMessage, Index expectedSelectedCardIndex) {
        executeCommand(command);
        expectedModel.updateFilteredDeckList(PREDICATE_SHOW_ALL_DECKS);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        if (expectedSelectedCardIndex != null) {
            assertSelectedCardChanged(expectedSelectedCardIndex);
        } else {
            assertSelectedCardUnchanged();
        }
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 4. Asserts that the command box has the error style.<br>
     * Verifications 1 and 2 are performed by
     * {@code AnakinSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
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
