package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AnakinCommandTestUtil.VALID_NAME_JOHN;
import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AnakinCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AnakinCommandTestUtil.showDeckAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_DECK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_DECK;
import static seedu.address.testutil.AnakinTypicalDecks.getTypicalAnakin;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.anakincommands.AnakinEditDeckCommand;
import seedu.address.logic.anakincommands.AnakinEditDeckCommand.EditDeckDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.AnakinModel;
import seedu.address.model.AnakinModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.anakindeck.AnakinDeck;
import seedu.address.testutil.EditDeckDescriptorBuilder;
import seedu.address.testutil.AnakinDeckBuilder;
import seedu.address.testutil.AnakinTypicalCards;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class AnakinEditDeckCommandTest {

    private AnakinModel model = new AnakinModelManager(getTypicalAnakin(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        AnakinDeck editedAnakinDeck = new AnakinDeckBuilder().build();
        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder(editedAnakinDeck).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(INDEX_FIRST_DECK, descriptor);

        String expectedMessage = String.format(AnakinEditDeckCommand.MESSAGE_EDIT_DECK_SUCCESS, editedAnakinDeck);

        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.updateDeck(model.getFilteredDeckList().get(0), editedAnakinDeck);
        expectedModel.commitAnakin();

        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAnakinDeck = Index.fromOneBased(model.getFilteredDeckList().size());
        AnakinDeck lastAnakinDeck = model.getFilteredDeckList().get(indexLastAnakinDeck.getZeroBased());

        AnakinDeckBuilder deckInList = new AnakinDeckBuilder(lastAnakinDeck);
        AnakinDeck editedAnakinDeck = deckInList.withName(VALID_NAME_JOHN).withCards(VALID_CARDS).build();

        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder().withName(VALID_NAME_JOHN)
                .withCards(AnakinTypicalCards.getTypicalCards()).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(indexLastAnakinDeck, descriptor);

        String expectedMessage = String.format(AnakinEditDeckCommand.MESSAGE_EDIT_DECK_SUCCESS, editedAnakinDeck);

        AnakinModel expectedModel = new AnakinModelManager(new AddressBook(model.getAnakin()), new UserPrefs());
        expectedModel.updateDeck(lastAnakinDeck, editedAnakinDeck);
        expectedModel.commitAnakin();

        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(INDEX_FIRST_DECK, new EditDeckDescriptor());
        AnakinDeck editedAnakinDeck = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());

        String expectedMessage = String.format(AnakinEditDeckCommand.MESSAGE_EDIT_DECK_SUCCESS, editedAnakinDeck);

        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.commitAnakin();

        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        AnakinDeck deckInFilteredList = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        AnakinDeck editedAnakinDeck = new AnakinDeckBuilder(deckInFilteredList).withName(VALID_NAME_JOHN).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(INDEX_FIRST_DECK,
                new EditDeckDescriptorBuilder().withName(VALID_NAME_JOHN).build());

        String expectedMessage = String.format(AnakinEditDeckCommand.MESSAGE_EDIT_DECK_SUCCESS, editedAnakinDeck);

        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.updateDeck(model.getFilteredDeckList().get(0), editedAnakinDeck);
        expectedModel.commitAnakin();

        assertCommandSuccess(editCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAnakinDeckUnfilteredList_failure() {
        AnakinDeck firstAnakinDeck = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder(firstAnakinDeck).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(INDEX_SECOND_DECK, descriptor);

        assertCommandFailure(editCommand, model, commandHistory, AnakinEditDeckCommand.MESSAGE_DUPLICATE_DECK);
    }

    @Test
    public void execute_duplicateAnakinDeckFilteredList_failure() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);

        // edit deck in filtered list into a duplicate in address book
        AnakinDeck deckInList = model.getAnakin().getDeckList().get(INDEX_SECOND_DECK.getZeroBased());
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(INDEX_FIRST_DECK,
                new EditDeckDescriptorBuilder(deckInList).build());

        assertCommandFailure(editCommand, model, commandHistory, AnakinEditDeckCommand.MESSAGE_DUPLICATE_DECK);
    }

    @Test
    public void execute_invalidAnakinDeckIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder().withName(VALID_NAME_JOHN).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidAnakinDeckIndexFilteredList_failure() {
        showDeckAtIndex(model, INDEX_FIRST_DECK);
        Index outOfBoundIndex = INDEX_SECOND_DECK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAnakin().getDeckList().size());

        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(outOfBoundIndex,
                new EditDeckDescriptorBuilder().withName(VALID_NAME_JOHN).build());

        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        AnakinDeck editedAnakinDeck = new AnakinDeckBuilder().build();
        AnakinDeck deckToEdit = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder(editedAnakinDeck).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(INDEX_FIRST_DECK, descriptor);
        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());
        expectedModel.updateDeck(deckToEdit, editedAnakinDeck);
        expectedModel.commitAnakin();

        // edit -> first deck edited
        editCommand.execute(model, commandHistory);

        // undo -> reverts anakin back to previous state and filtered deck list to show all decks
        expectedModel.undoAnakin();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first deck edited again
        expectedModel.redoAnakin();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder().withName(VALID_NAME_JOHN).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(outOfBoundIndex, descriptor);

        // execution failed -> address book state not added into model
        assertCommandFailure(editCommand, model, commandHistory, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);

        // single address book state in model -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code AnakinDeck} from a filtered list.
     * 2. Undo the edit.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited deck in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the deck object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameAnakinDeckEdited() throws Exception {
        AnakinDeck editedAnakinDeck = new AnakinDeckBuilder().build();
        EditDeckDescriptor descriptor = new EditDeckDescriptorBuilder(editedAnakinDeck).build();
        AnakinEditDeckCommand editCommand = new AnakinEditDeckCommand(INDEX_FIRST_DECK, descriptor);
        AnakinModelManager expectedModel = new AnakinModelManager(model.getAnakin(), new UserPrefs());

        showDeckAtIndex(model, INDEX_SECOND_DECK);
        AnakinDeck deckToEdit = model.getFilteredDeckList().get(INDEX_FIRST_DECK.getZeroBased());
        expectedModel.updateDeck(deckToEdit, editedAnakinDeck);
        expectedModel.commitAnakin();

        // edit -> edits second deck in unfiltered deck list / first deck in filtered deck list
        editCommand.execute(model, commandHistory);

        // undo -> reverts anakin back to previous state and filtered deck list to show all decks
        expectedModel.undoAnakin();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(model.getFilteredAnakinDeckList().get(INDEX_FIRST_DECK.getZeroBased()), deckToEdit);
        // redo -> edits same second deck in unfiltered deck list
        expectedModel.redoAnakin();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final AnakinEditDeckCommand standardCommand = new AnakinEditDeckCommand(INDEX_FIRST_DECK, DESC_AMY);

        // same values -> returns true
        EditDeckDescriptor copyDescriptor = new EditDeckDescriptor(DESC_AMY);
        AnakinEditDeckCommand commandWithSameValues = new AnakinEditDeckCommand(INDEX_FIRST_DECK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AnakinEditDeckCommand(INDEX_SECOND_DECK, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new AnakinEditDeckCommand(INDEX_FIRST_DECK, DESC_BOB)));
    }

}
