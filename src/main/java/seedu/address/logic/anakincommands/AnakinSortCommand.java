package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AnakinModel;

/**
 * Sort the list of deck in lexicographical order.
 */
public class AnakinSortCommand extends AnakinCommand{

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "sort all decks/cards in alphabetical order";

    @Override
    public CommandResult execute(AnakinModel anakinModel, CommandHistory history) {
        requireNonNull(anakinModel);
        anakinModel.sort();
        anakinModel.commitAnakin();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
