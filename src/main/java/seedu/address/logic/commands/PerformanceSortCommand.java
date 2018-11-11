package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_CURRENTLY_REVIEWING_DECK;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CARD_LEVEL_OPERATION;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Sort the list of deck in order of next review date.
 */
public class PerformanceSortCommand extends Command {

    public static final String COMMAND_WORD = "rank";
    public static final String MESSAGE_SUCCESS = "Cards are sorted by performance";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (model.isReviewingDeck()) {
            throw new CommandException(MESSAGE_CURRENTLY_REVIEWING_DECK);
        }

        if (!model.isInsideDeck()) {
            throw new CommandException(MESSAGE_INVALID_CARD_LEVEL_OPERATION);
        }
        model.sort(ModelManager.SortingType.PERFORMANCE);
        model.commitAnakin(COMMAND_WORD);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
