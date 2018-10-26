package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.AddressbookTypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.AddressbookTypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.AddressbookTypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.commons.core.AddressbookMessages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the AddressbookModel, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class AddressbookDeleteCommandTest {

    private AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(),
        new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        AddressbookModelManagerAddressbook expectedModel = new AddressbookModelManagerAddressbook(
            addressbookModel.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);
        expectedModel.commitAddressBook();

        assertCommandSuccess(deleteCommand, addressbookModel, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(addressbookModel.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, addressbookModel, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(addressbookModel, INDEX_FIRST_PERSON);

        Person personToDelete = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            addressbookModel.getAddressBook(), new UserPrefs());
        expectedAddressbookModel.deletePerson(personToDelete);
        expectedAddressbookModel.commitAddressBook();
        showNoPerson(expectedAddressbookModel);

        assertCommandSuccess(deleteCommand, addressbookModel, commandHistory, expectedMessage,
            expectedAddressbookModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(addressbookModel, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < addressbookModel.getAddressBook().getPersonList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, addressbookModel, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Person personToDelete = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            addressbookModel.getAddressBook(), new UserPrefs());
        expectedAddressbookModel.deletePerson(personToDelete);
        expectedAddressbookModel.commitAddressBook();

        // delete -> first person deleted
        deleteCommand.execute(addressbookModel, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedAddressbookModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        // redo -> same first person deleted again
        expectedAddressbookModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(addressbookModel.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        // execution failed -> address book state not added into addressbookModel
        assertCommandFailure(deleteCommand, addressbookModel, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // single address book state in addressbookModel -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Person} from a filtered list.
     * 2. Undo the deletion.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously deleted person in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the deletion. This ensures {@code RedoCommand} deletes the person object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_samePersonDeleted() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            addressbookModel.getAddressBook(), new UserPrefs());

        showPersonAtIndex(addressbookModel, INDEX_SECOND_PERSON);
        Person personToDelete = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedAddressbookModel.deletePerson(personToDelete);
        expectedAddressbookModel.commitAddressBook();

        // delete -> deletes second person in unfiltered person list / first person in filtered person list
        deleteCommand.execute(addressbookModel, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedAddressbookModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        assertNotEquals(personToDelete,
            addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()));
        // redo -> deletes same second person in unfiltered person list
        expectedAddressbookModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code addressbookModel}'s filtered list to show no one.
     */
    private void showNoPerson(AddressbookModel addressbookModel) {
        addressbookModel.updateFilteredPersonList(p -> false);

        assertTrue(addressbookModel.getFilteredPersonList().isEmpty());
    }
}
