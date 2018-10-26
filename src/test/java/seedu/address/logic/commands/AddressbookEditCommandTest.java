package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.VALID_TAG_HUSBAND;
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
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressbookEditPersonDescriptorBuilder;
import seedu.address.testutil.AddressbookPersonBuilder;

/**
 * Contains integration tests (interaction with the AddressbookModel, UndoCommand and RedoCommand) and unit tests for
 * EditCommand.
 */
public class AddressbookEditCommandTest {

    private AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(),
        new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person editedPerson = new AddressbookPersonBuilder().build();
        EditPersonDescriptor descriptor = new AddressbookEditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            new AddressBook(addressbookModel.getAddressBook()), new UserPrefs());
        expectedAddressbookModel.updatePerson(addressbookModel.getFilteredPersonList().get(0), editedPerson);
        expectedAddressbookModel.commitAddressBook();

        assertCommandSuccess(editCommand, addressbookModel, commandHistory, expectedMessage, expectedAddressbookModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPerson = Index.fromOneBased(addressbookModel.getFilteredPersonList().size());
        Person lastPerson = addressbookModel.getFilteredPersonList().get(indexLastPerson.getZeroBased());

        AddressbookPersonBuilder personInList = new AddressbookPersonBuilder(lastPerson);
        Person editedPerson = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND).build();

        EditPersonDescriptor descriptor = new AddressbookEditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPerson, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            new AddressBook(addressbookModel.getAddressBook()), new UserPrefs());
        expectedAddressbookModel.updatePerson(lastPerson, editedPerson);
        expectedAddressbookModel.commitAddressBook();

        assertCommandSuccess(editCommand, addressbookModel, commandHistory, expectedMessage, expectedAddressbookModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditPersonDescriptor());
        Person editedPerson = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            new AddressBook(addressbookModel.getAddressBook()), new UserPrefs());
        expectedAddressbookModel.commitAddressBook();

        assertCommandSuccess(editCommand, addressbookModel, commandHistory, expectedMessage, expectedAddressbookModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(addressbookModel, INDEX_FIRST_PERSON);

        Person personInFilteredList = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new AddressbookPersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new AddressbookEditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            new AddressBook(addressbookModel.getAddressBook()), new UserPrefs());
        expectedAddressbookModel.updatePerson(addressbookModel.getFilteredPersonList().get(0), editedPerson);
        expectedAddressbookModel.commitAddressBook();

        assertCommandSuccess(editCommand, addressbookModel, commandHistory, expectedMessage, expectedAddressbookModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Person firstPerson = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new AddressbookEditPersonDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, addressbookModel, commandHistory, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(addressbookModel, INDEX_FIRST_PERSON);

        // edit person in filtered list into a duplicate in address book
        Person personInList = addressbookModel.getAddressBook().getPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new AddressbookEditPersonDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, addressbookModel, commandHistory, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(addressbookModel.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new AddressbookEditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, addressbookModel, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(addressbookModel, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < addressbookModel.getAddressBook().getPersonList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new AddressbookEditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, addressbookModel, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Person editedPerson = new AddressbookPersonBuilder().build();
        Person personToEdit = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditPersonDescriptor descriptor = new AddressbookEditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            new AddressBook(addressbookModel.getAddressBook()), new UserPrefs());
        expectedAddressbookModel.updatePerson(personToEdit, editedPerson);
        expectedAddressbookModel.commitAddressBook();

        // edit -> first person edited
        editCommand.execute(addressbookModel, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedAddressbookModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        // redo -> same first person edited again
        expectedAddressbookModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(addressbookModel.getFilteredPersonList().size() + 1);
        EditPersonDescriptor descriptor = new AddressbookEditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        // execution failed -> address book state not added into addressbookModel
        assertCommandFailure(editCommand, addressbookModel, commandHistory,
            AddressbookMessages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // single address book state in addressbookModel -> undoCommand and redoCommand fail
        assertCommandFailure(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code Person} from a filtered list.
     * 2. Undo the edit.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited person in the
     * unfiltered list is different from the index at the filtered list.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the person object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_samePersonEdited() throws Exception {
        Person editedPerson = new AddressbookPersonBuilder().build();
        EditPersonDescriptor descriptor = new AddressbookEditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            new AddressBook(addressbookModel.getAddressBook()), new UserPrefs());

        showPersonAtIndex(addressbookModel, INDEX_SECOND_PERSON);
        Person personToEdit = addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedAddressbookModel.updatePerson(personToEdit, editedPerson);
        expectedAddressbookModel.commitAddressBook();

        // edit -> edits second person in unfiltered person list / first person in filtered person list
        editCommand.execute(addressbookModel, commandHistory);

        // undo -> reverts addressbook back to previous state and filtered person list to show all persons
        expectedAddressbookModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        assertNotEquals(addressbookModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()), personToEdit);
        // redo -> edits same second person in unfiltered person list
        expectedAddressbookModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditPersonDescriptor copyDescriptor = new EditPersonDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
