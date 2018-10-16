package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AnakinModel;


/**
 * Lists all persons in the address book to the user.
 */
public class AnakinListCommand extends AnakinCommand {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all decks";


    @Override
    public CommandResult execute(AnakinModel model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredDeckList(unusedVariable -> true);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
