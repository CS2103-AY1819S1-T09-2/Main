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

public class AddressbookUndoCommandTest {

    private final AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(),
        new UserPrefs());
    private final AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
        getTypicalAddressBook(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        // set up of models' undo/redo history
        deleteFirstPerson(addressbookModel);
        deleteFirstPerson(addressbookModel);

        deleteFirstPerson(expectedAddressbookModel);
        deleteFirstPerson(expectedAddressbookModel);
    }

    @Test
    public void execute() {
        // multiple undoable states in addressbookModel
        expectedAddressbookModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        // single undoable state in addressbookModel
        expectedAddressbookModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);

        // no undoable states in addressbookModel
        assertCommandFailure(new UndoCommand(), addressbookModel, commandHistory, UndoCommand.MESSAGE_FAILURE);
    }
}
