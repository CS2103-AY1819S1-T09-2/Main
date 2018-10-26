package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.AddressbookTypicalPersons.getTypicalAddressBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;
import seedu.address.model.UserPrefs;

public class AddressbookClearCommandTest {

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_emptyAddressBook_success() {
        AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook();
        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook();
        expectedAddressbookModel.commitAddressBook();

        assertCommandSuccess(new ClearCommand(), addressbookModel, commandHistory, ClearCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(),
            new UserPrefs());
        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(),
            new UserPrefs());
        expectedAddressbookModel.resetData(new AddressBook());
        expectedAddressbookModel.commitAddressBook();

        assertCommandSuccess(new ClearCommand(), addressbookModel, commandHistory, ClearCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }

}
