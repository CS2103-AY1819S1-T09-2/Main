package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.AddressbookTypicalPersons.getTypicalAddressBook;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressbookPersonBuilder;

/**
 * Contains integration tests (interaction with the AddressbookModel) for {@code AddCommand}.
 */
public class AddressbookAddCommandIntegrationTest {

    private AddressbookModel addressbookModel;
    private CommandHistory commandHistory = new CommandHistory();

    @Before
    public void setUp() {
        addressbookModel = new AddressbookModelManagerAddressbook(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new AddressbookPersonBuilder().build();

        AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook(
            addressbookModel.getAddressBook(), new UserPrefs());
        expectedAddressbookModel.addPerson(validPerson);
        expectedAddressbookModel.commitAddressBook();

        assertCommandSuccess(new AddCommand(validPerson), addressbookModel, commandHistory,
            String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedAddressbookModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = addressbookModel.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), addressbookModel, commandHistory,
            AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
