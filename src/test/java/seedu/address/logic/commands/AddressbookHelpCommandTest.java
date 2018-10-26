package seedu.address.logic.commands;

import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.AddressbookCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ShowHelpRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressbookModel;
import seedu.address.model.AddressbookModelManagerAddressbook;
import seedu.address.ui.testutil.EventsCollectorRule;

public class AddressbookHelpCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private AddressbookModel addressbookModel = new AddressbookModelManagerAddressbook();
    private AddressbookModel expectedAddressbookModel = new AddressbookModelManagerAddressbook();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_help_success() {
        assertCommandSuccess(new HelpCommand(), addressbookModel, commandHistory, SHOWING_HELP_MESSAGE,
            expectedAddressbookModel);
        assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ShowHelpRequestEvent);
        assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }
}
