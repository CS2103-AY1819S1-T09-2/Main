package seedu.address.logic.anakincommands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AnakinModel;

/**
 * Format full help instructions for every command for display.
 */
public class AnakinHelpCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    @Override
    public CommandResult execute(AnakinModel model, CommandHistory history) {
        //TODO: Retrieve Help Message for each of the commands
        return new CommandResult(SHOWING_HELP_MESSAGE);
    }
}
