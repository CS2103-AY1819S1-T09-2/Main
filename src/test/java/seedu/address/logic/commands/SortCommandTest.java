package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SUCCESS_1;
import static seedu.address.logic.commands.SortCommand.MESSAGE_SUCCESS_2;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakin;
import static seedu.address.testutil.TypicalDecks.getTypicalAnakinInDeck;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class SortCommandTest {

    private final CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_sortDeckList() {
        Model actualModel = new ModelManager(getTypicalAnakin(), new UserPrefs());
        SortCommand sortCommand = new SortCommand();

        String expectedMessage = String.format(MESSAGE_SUCCESS_1);

        Model expectedModel = new ModelManager(actualModel.getAnakin(), new UserPrefs());
        expectedModel.sort();
        expectedModel.commitAnakin(SortCommand.COMMAND_WORD);

        assertCommandSuccess(sortCommand, actualModel, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_sortCardList() {
        Model actualModel = new ModelManager(getTypicalAnakinInDeck(), new UserPrefs());
        SortCommand sortCommand = new SortCommand();

        String expectedMessage = String.format(MESSAGE_SUCCESS_2);

        Model expectedModel = new ModelManager(actualModel.getAnakin(), new UserPrefs());
        expectedModel.sort();
        expectedModel.commitAnakin(SortCommand.COMMAND_WORD);

        assertCommandSuccess(sortCommand, actualModel, commandHistory, expectedMessage, expectedModel);
    }
}
