package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.AddressbookTypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.AddressbookTypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the AddressbookModel) and unit tests for ListCommand.
 */
public class AddressbookListCommandTest {

    private AddressbookModel addressbookModel;
    private AddressbookModel expectedAddressbookModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        addressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(), new UserPrefs());
        expectedAddressbookModel = new AddressbookModelManagerAddressbook(addressbookModel.getAddressBook(),
            new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), addressbookModel, commandHistory, ListCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(addressbookModel, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), addressbookModel, commandHistory, ListCommand.MESSAGE_SUCCESS,
            expectedAddressbookModel);
    }
}
