package seedu.address.logic.anakincommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;

/**
 * Sort the list of deck in lexicographical order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS_1 = "decks are sorted in alphabetical order";
    public static final String MESSAGE_SUCCESS_2 = "cards are sorted in alphabetical order";

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.sort();
        model.commitAnakin();
        if (model.isInsideDeck()) {
            return new CommandResult(MESSAGE_SUCCESS_2);
        } else {
            return new CommandResult(MESSAGE_SUCCESS_1);
        }
    }
}
