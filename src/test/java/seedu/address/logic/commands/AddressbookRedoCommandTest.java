package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.deleteFirstPerson;
import static seedu.address.testutil.AddressbookTypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;
import seedu.address.model.UserPrefs;

public class AddressbookRedoCommandTest {

    private final AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(),
        new UserPrefs());
    private final AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
        getTypicalAddressBook(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of both models' undo/redo history
        deleteFirstPerson(addressbookModel);
        deleteFirstPerson(addressbookModel);
        addressbookModel.undoAddressBook();
        addressbookModel.undoAddressBook();

        deleteFirstPerson(expectedAddressbookModel);
        deleteFirstPerson(expectedAddressbookModel);
        expectedAddressbookModel.undoAddressBook();
        expectedAddressbookModel.undoAddressBook();
    }

    @Test
    public void execute() {
        // multiple redoable states in addressbookModel
        expectedAddressbookModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        // single redoable state in addressbookModel
        expectedAddressbookModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        // no redoable state in addressbookModel
        assertCommandFailure(new RedoCommand(), addressbookModel, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
}
