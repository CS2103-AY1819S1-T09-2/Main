package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AnakinModel;

/**
 * Sort the list of deck in lexicographical order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS_1 = "Decks are sorted in alphabetical order";
    public static final String MESSAGE_SUCCESS_2 = "Cards are sorted in alphabetical order";

    @Override
    public CommandResult execute(AnakinModel anakinModel, CommandHistory history) {
        requireNonNull(anakinModel);
        anakinModel.sort();
        anakinModel.commitAnakin();
        if (anakinModel.isInsideDeck()) {
            return new CommandResult(MESSAGE_SUCCESS_2);
        } else {
            return new CommandResult(MESSAGE_SUCCESS_1);
        }
    }
}
