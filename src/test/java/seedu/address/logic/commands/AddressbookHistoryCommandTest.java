package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandSuccess;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;

public class AddressbookHistoryCommandTest {
    private CommandHistory history = new CommandHistory();
    private AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook();
    private AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook();

    @Test
    public void execute() {
        assertCommandSuccess(new HistoryCommand(), addressbookModel, history, HistoryCommand.MESSAGE_NO_HISTORY,
            expectedAddressbookModel);

        String command1 = "clear";
        history.add(command1);
        assertCommandSuccess(new HistoryCommand(), addressbookModel, history,
            String.format(HistoryCommand.MESSAGE_SUCCESS, command1), expectedAddressbookModel);

        String command2 = "randomCommand";
        String command3 = "select 1";
        history.add(command2);
        history.add(command3);

        String expectedMessage = String.format(HistoryCommand.MESSAGE_SUCCESS,
            String.join("\n", command3, command2, command1));
        assertCommandSuccess(new HistoryCommand(), addressbookModel, history, expectedMessage,
            expectedAddressbookModel);
    }

}
